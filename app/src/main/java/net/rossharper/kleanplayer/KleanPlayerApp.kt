package net.rossharper.kleanplayer

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import net.rossharper.kleanplayer.home.view.HomeViewModel
import net.rossharper.kleanplayer.home.view.createHomeViewModel
import net.rossharper.kleanplayer.main.MainViewModel
import net.rossharper.kleanplayer.main.createMainViewModel

class KleanPlayerApp : Application() {
    val viewModelProviderFactory = ViewModelProviderFactory(this)
}

class ViewModelProviderFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> createMainViewModel()
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> createHomeViewModel(context)
            else -> throw IllegalArgumentException("Unknown Model Class Name")
        } as T // TODO: can the unchecked cast be dealt with?
    }
}




