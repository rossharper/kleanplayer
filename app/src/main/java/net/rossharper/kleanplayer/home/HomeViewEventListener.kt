package net.rossharper.kleanplayer.home

interface HomeViewEventListener {
    fun onHomeViewLoad()
    fun onRefresh()
    fun onRetry()
    fun onItemSelected(sectionPosition: Int, itemPosition: Int)
}