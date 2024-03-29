package one.and.frankie.quizio.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import one.and.frankie.quizio.di.DaggerApiComponent
import one.and.frankie.quizio.f
import one.and.frankie.quizio.model.QA
import one.and.frankie.quizio.model.QAsService
import javax.inject.Inject

class CardsViewModel : ViewModel() {
    @Inject lateinit var qAsService: QAsService
    private val disposable = CompositeDisposable()

    val qas = MutableLiveData<List<QA>>()
    val isFetching = MutableLiveData<Boolean>()
    val fetchFailed = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun refresh() {
        fetchQAs()
    }

    @SuppressLint("NewApi")
    private fun fetchQAs() {
        isFetching.value = true
        disposable.add(
            qAsService.getQAs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<QA>>() {
                    override fun onSuccess(qas: List<QA>) {
                        // Important: limit the character length of the answer to 400 to prevent overflowing text.
                        for (qa in qas) {
                            if (qa.answer!!.length >= 400) {
                                qa.shortenedAnswer = qa.answer.substring(0..397)
                                qa.shortenedAnswer += "..."
                            }
                        }
                        this@CardsViewModel.qas.value = qas
                        isFetching.value = false
                        fetchFailed.value = false
                    }

                    override fun onError(e: Throwable) {
                        isFetching.value = false
                        fetchFailed.value = true
                        f.log(e.message)
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}