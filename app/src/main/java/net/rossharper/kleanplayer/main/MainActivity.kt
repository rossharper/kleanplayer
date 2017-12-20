package net.rossharper.kleanplayer.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import net.rossharper.kleanplayer.KleanPlayerApp
import net.rossharper.kleanplayer.R
import net.rossharper.kleanplayer.home.view.android.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders
                .of(this, (application as KleanPlayerApp).viewModelProviderFactory)
                .get(MainViewModel::class.java)

        viewModel.mainViewLiveData
                .observe(this, Observer<MainViewState> {
                    viewState ->
                    viewState?.let{
                        if(viewState is MainViewState.Home) {
                            displayHome()
                        }
                    }
                })

        viewModel.onMainViewLoad()
    }

    private fun displayHome() {
        showFragment(HomeFragment())
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(
                R.id.container,
                fragment
        ).commit()
    }
}
