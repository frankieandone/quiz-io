package one.and.frankie.quizio.view

import android.os.SystemClock
import android.view.View

abstract class DoubleClickListener(clickEventsIntervalMs: Long = DEFAULT_QUALIFYING_DOUBLE_CLICK_INTERVAL_MS) :
    View.OnClickListener {

    private var qualifyingDoubleClickIntervalMs: Long = clickEventsIntervalMs
    private var timestampLastClick: Long = 0

    companion object {
        private const val DEFAULT_QUALIFYING_DOUBLE_CLICK_INTERVAL_MS = 200L
    }

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - timestampLastClick < qualifyingDoubleClickIntervalMs) {
            onDoubleClick()
        }
        timestampLastClick = SystemClock.elapsedRealtime()
    }

    abstract fun onDoubleClick()
}