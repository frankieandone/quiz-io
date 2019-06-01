package one.and.frankie.quizio.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import one.and.frankie.quizio.R
import one.and.frankie.quizio.model.QA

class MainActivity : AppCompatActivity() {
    private lateinit var cardFragment: CardFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initQuestionViewFragment()
    }

    private fun initQuestionViewFragment() {
        cardFragment = CardFragment()
        supportFragmentManager.beginTransaction().add(R.id.fl_container, cardFragment).commit()
    }
}
