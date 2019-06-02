package one.and.frankie.quizio.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.card_back_full.*
import one.and.frankie.quizio.R

class CardBackFullActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_back_full)

        // The answer must be sent in the launching intent.
        tv_answer.text = intent.getStringExtra("answer")
        efab_expand_less.setOnClickListener {
            // Delete current view and go back to the previous activity.
            finish()
        }
    }
}