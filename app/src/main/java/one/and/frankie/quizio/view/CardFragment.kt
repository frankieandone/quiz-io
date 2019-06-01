package one.and.frankie.quizio.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_card.*
import one.and.frankie.quizio.R
import one.and.frankie.quizio.viewmodel.CardsViewModel

class CardFragment : Fragment() {
    private lateinit var cardsViewModel: CardsViewModel
    private lateinit var cardsViewPager: ViewPager2
    private val cardAdapter = CardAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_card, container, false) as SwipeRefreshLayout

        cardsViewModel = ViewModelProviders.of(this).get(CardsViewModel::class.java)
        cardsViewModel.refresh()

        cardsViewPager = rootView.findViewById(R.id.vp_cards) as ViewPager2
        cardsViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        cardsViewPager.adapter = cardAdapter

        observeCardsViewModel()

        rootView.setOnRefreshListener {
            cardsViewModel.refresh()
            rootView.isRefreshing = false
        }
        return rootView
    }

    private fun observeCardsViewModel() {
        cardsViewModel.qas.observe(this, Observer { qas ->
            if (qas != null) {
                cardAdapter.updateCards(qas)
                cardsViewPager.visibility = View.VISIBLE
            }
        })
        cardsViewModel.isFetching.observe(this, Observer { isFetching ->
            if (isFetching != null) {
                if (isFetching) {
                    cardsViewPager.visibility = View.VISIBLE
                    tv_fetch_failed.visibility = View.GONE
                    pb_fetching.visibility = View.VISIBLE
                } else {
                    pb_fetching.visibility = View.GONE
                }
            }
        })
        cardsViewModel.fetchFailed.observe(this, Observer { fetchFailed ->
            if (fetchFailed != null) {
                if (fetchFailed) {
                    tv_fetch_failed.visibility = View.VISIBLE
                } else {
                    tv_fetch_failed.visibility = View.GONE
                }
            }
        })
    }
}