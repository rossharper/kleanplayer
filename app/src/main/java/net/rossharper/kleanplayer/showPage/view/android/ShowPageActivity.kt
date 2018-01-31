package net.rossharper.kleanplayer.showPage.view.android

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.rossharper.kleanplayer.KleanPlayerApp
import net.rossharper.kleanplayer.R
import net.rossharper.kleanplayer.showPage.ShowId
import net.rossharper.kleanplayer.showPage.view.ShowPageViewModel

class ShowPageActivity : AppCompatActivity() {

    private lateinit var viewModel : ShowPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_page)

        viewModel = ViewModelProviders
                .of(this, (applicationContext as KleanPlayerApp).viewModelProviderFactory)
                .get(ShowPageViewModel::class.java)

        val showId = intent.showId ?: throw IllegalStateException("Error, no show id provided")

        viewModel.controller.onShowPageLoad(showId)
    }

    private val Intent.showId: ShowId?
        get() {
            return extras.getString(ShowIdExtra)?.let {
                ShowId(it)
        }
    }

    companion object {
        val ShowIdExtra = "showid"
        fun launchIntent(launchContext: Context, showId: ShowId): Intent {
            return Intent(launchContext, ShowPageActivity::class.java).apply {
                putExtra(ShowIdExtra, showId.id)
            }
        }
    }
}