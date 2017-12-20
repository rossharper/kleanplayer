package net.rossharper.kleanplayer.showPage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_show_page.*
import net.rossharper.kleanplayer.R

class ShowPageActivity : AppCompatActivity() {

    companion object {
        val ShowIdExtra = "showid"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_page)

        showid.text = intent.extras.getString(ShowIdExtra)
    }
}