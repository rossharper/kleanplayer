package net.rossharper.kleanplayer.showPage

import android.content.Context
import android.util.Log
import net.rossharper.kleanplayer.showPage.view.android.ShowPageActivity

fun launchShowPage(launchContext: Context, showId: ShowId) {
    Log.i("TODO", "Launch show page ${showId}")
    launchContext.startActivity(ShowPageActivity.launchIntent(launchContext, showId))
}