package net.rossharper.kleanplayer.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

fun createMainViewModel(): MainViewModel {
  return MainViewModel()
}

class MainViewModel : ViewModel(), MainViewEventsListener {

    val mainViewLiveData = MutableLiveData<MainViewState>()

    override fun onMainViewLoad() {
        mainViewLiveData.value = MainViewState.Home()
    }
}

sealed class MainViewState {
    class Home : MainViewState()
}

interface MainViewEventsListener {
    fun onMainViewLoad()
}