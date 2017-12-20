package net.rossharper.kleanplayer.home.router

import android.util.Log
import net.rossharper.kleanplayer.home.domain.Item

class HomeRouter {
    fun launchShowPage(showItem: Item.ShowItem) {
        /*
        This is where we'd like to launch the onward show page.
        Would be launched as an activity via an intent.
        However, we need a context for that, how to get one to the router?
        Does this router pattern not work when using AndroidViewModel to hang all the arch
        off of?
        Should the message actually go via the AndroidViewModel for the owning Fragment/Activity to
        handle?
         */
        Log.i("TODO", "Launch show page ${showItem.title}")
    }
}