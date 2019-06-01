package one.and.frankie.quizio.view

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card.view.*
import kotlinx.android.synthetic.main.card_back.view.*
import kotlinx.android.synthetic.main.card_front.view.*
import one.and.frankie.quizio.R
import one.and.frankie.quizio.model.QA

class CardAdapter(private val cards: ArrayList<QA>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    class CardViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        // Only keep references to dynamic UI.
        private val tvQuestion: TextView
        private val tvAnswer: TextView
        private var isFrontVisible = true
        private var flipInAnim: AnimatorSet
        private var flipOutAnim: AnimatorSet
        private val doubleClickListener = object : DoubleClickListener() {
            override fun onDoubleClick() {
                flipCard()
            }
        }

        init {
            val context = view.context
            tvQuestion = view.card_front.cv_question.tv_question
            tvAnswer = view.card_back.cv_answer.tv_answer
            flipInAnim = AnimatorInflater.loadAnimator(context, R.animator.flip_in) as AnimatorSet
            flipOutAnim = AnimatorInflater.loadAnimator(context, R.animator.flip_out) as AnimatorSet
            val distance = 8000
            val scale = context.resources.displayMetrics.density * distance
            view.card_front.cameraDistance = scale
            view.card_back.cameraDistance = scale
            view.setOnClickListener(doubleClickListener)
        }

        fun bind(qa: QA) {
            tvQuestion.text = qa.question
            tvAnswer.text = qa.answer
        }

        private fun flipCard() = when {
            isFrontVisible -> {
                flipOutAnim.setTarget(view.card_front)
                flipInAnim.setTarget(view.card_back)
                flipOutAnim.start()
                flipInAnim.start()
                isFrontVisible = false
            }
            else -> {
                flipOutAnim.setTarget(view.card_back)
                flipInAnim.setTarget(view.card_front)
                flipOutAnim.start()
                flipInAnim.start()
                isFrontVisible = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        CardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false))

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) = holder.bind(cards[position])

    override fun getItemCount(): Int = cards.size

    fun updateCards(newCards: List<QA>) {
        cards.clear()
        cards.addAll(newCards)
        notifyDataSetChanged()
    }
}