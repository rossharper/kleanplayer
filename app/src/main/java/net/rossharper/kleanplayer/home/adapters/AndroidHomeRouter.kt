package net.rossharper.kleanplayer.home.adapters

import android.content.Context
import net.rossharper.kleanplayer.home.HomeRouter
import net.rossharper.kleanplayer.home.domain.Item
import net.rossharper.kleanplayer.showPage.ShowId

class AndroidHomeRouter(private val launchContext: Context) : HomeRouter {
    override fun launchShowPage(showItem: Item.ShowItem) {
        net.rossharper.kleanplayer.showPage.launchShowPage(launchContext, ShowId(showItem.id))
    }
}