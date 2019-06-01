package one.and.frankie.quizio.view

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import one.and.frankie.quizio.R

class CardFragment : Fragment() {
    private var isFrontVisible = true
    private lateinit var flipInAnim: AnimatorSet
    private lateinit var flipOutAnim: AnimatorSet
    private lateinit var cardFront: View
    private lateinit var cardBack: View
    private val doubleClickListener = object : DoubleClickListener() {
        override fun onDoubleClick() {
            flipCard()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_card, container, false)
        cardFront = rootView.findViewById(R.id.card_front)
        cardBack = rootView.findViewById(R.id.card_back)
        flipInAnim = AnimatorInflater.loadAnimator(rootView.context, R.animator.flip_in) as AnimatorSet
        flipOutAnim = AnimatorInflater.loadAnimator(rootView.context, R.animator.flip_out) as AnimatorSet
        val distance = 8000
        val scale = resources.displayMetrics.density * distance
        cardFront.cameraDistance = scale
        cardBack.cameraDistance = scale
        rootView.setOnClickListener(doubleClickListener)
        return rootView
    }

    private fun flipCard() = when {
        isFrontVisible -> {
            flipOutAnim.setTarget(cardFront)
            flipInAnim.setTarget(cardBack)
            flipOutAnim.start()
            flipInAnim.start()
            isFrontVisible = false
        }
        else -> {
            flipOutAnim.setTarget(cardBack)
            flipInAnim.setTarget(cardFront)
            flipOutAnim.start()
            flipInAnim.start()
            isFrontVisible = true
        }
    }
}