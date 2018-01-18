package net.rossharper.kleanplayer.home

import net.rossharper.kleanplayer.home.domain.Item

interface HomeRouter {
    fun launchShowPage(showItem: Item.ShowItem)
}