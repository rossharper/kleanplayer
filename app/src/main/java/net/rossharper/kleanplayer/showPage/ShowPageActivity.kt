package net.rossharper.kleanplayer.showPage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_show_page.*
import net.rossharper.kleanplayer.R

class ShowPageActivity : AppCompatActivity() {

    companion object {
        val ShowIdExtra = "showid"
        fun launchIntent(launchContext: Context, showId: ShowId): Intent {
            return Intent(launchContext, ShowPageActivity::class.java).apply {
                putExtra(ShowPageActivity.ShowIdExtra, showId.id)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_page)

        showid.text = intent.showId?.id ?: "Error, no show id provided"
    }

    private val Intent.showId: ShowId?
        get() {
            return extras.getString(ShowIdExtra)?.let {
                ShowId(it)
        }
    }
}