package net.rossharper.kleanplayer.home.domain

data class HomeStream(val sections: List<Section>)
data class Section(val id: String, val title: String)