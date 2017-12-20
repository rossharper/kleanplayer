package net.rossharper.kleanplayer.home.usecases

import net.rossharper.kleanplayer.home.domain.HomeState
import net.rossharper.kleanplayer.home.domain.Item
import net.rossharper.kleanplayer.home.domain.StateHolder

interface SelectItemInput {
    fun execute(sectionPosition: Int, itemPosition: Int)
}

interface SelectItemOutput {
    fun launchShowPage(showItem: Item.ShowItem)
}

class SelectItemInteractor(private val stateHolder: StateHolder, private val output: SelectItemOutput) : SelectItemInput {
    override fun execute(sectionPosition: Int, itemPosition: Int) {

        val state = stateHolder.state as? HomeState.Loaded

        state?.let {
            val showItem = state.homeStream.sections[sectionPosition].items[itemPosition] as? Item.ShowItem
            showItem?.let {
                // TODO: send a stat
                output.launchShowPage(it)
            }
        }
    }
}
