package net.rossharper.kleanplayer.home.view

data class HomeStreamViewModel(val sectionViewModels: List<SectionViewModel>)
data class SectionViewModel(val title: String)