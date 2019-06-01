package one.and.frankie.quizio.model

import io.reactivex.Single
import one.and.frankie.quizio.di.DaggerApiComponent
import javax.inject.Inject

class QAsService {
    @Inject lateinit var api: QAsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getQAs(): Single<List<QA>> = api.getQAs()
}