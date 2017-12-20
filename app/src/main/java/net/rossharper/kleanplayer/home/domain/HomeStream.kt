package net.rossharper.kleanplayer.home.domain

data class HomeStream(val sections: List<Section>)
data class Section(val id: String, val title: String, val items: List<Item>)
sealed class Item {
    data class ShowItem(val id: String, val title: String) : Item()
}