package net.rossharper.kleanplayer.home.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import net.rossharper.kleanplayer.KleanPlayerApp
import net.rossharper.kleanplayer.R
import net.rossharper.kleanplayer.home.presenter.HomeViewState
import net.rossharper.kleanplayer.home.view.sections.HomeSectionsAdapter


class HomeFragment : Fragment() {

    private lateinit var viewModel : HomeViewModel

    private val adapter = HomeSectionsAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_home, container, false)
        viewModel = ViewModelProviders
                .of(this, (context.applicationContext as KleanPlayerApp)
                        .viewModelProviderFactory)
                .get(HomeViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        retryButton.setOnClickListener {
            viewModel.onRetry()
        }

        viewModel.homeViewLiveData.observe({ this.lifecycle }) {
            it?.transitionTo()
        }

        viewModel.onHomeViewLoad()
    }

    private fun HomeViewState.transitionTo() {
        when(this) {
            is HomeViewState.Loading -> {
                showLoadingView()
            }
            is HomeViewState.Error -> {
                showErrorView()
                hideDataView()
                hideLoadingView()
            }
            is HomeViewState.Success -> {
                showDataView()
                hideErrorView()
                hideLoadingView()
                adapter.items = homeStreamViewModel.sectionViewModels
            }
        }
    }

    private fun showLoadingView() {
        swipeRefreshLayout.isRefreshing = false
        loadingView.visibility = View.VISIBLE
    }

    private fun showErrorView() {
        errorView.visibility = View.VISIBLE
    }

    private fun showDataView() {
        swipeRefreshLayout.visibility = View.VISIBLE
    }

    private fun hideErrorView() {
        errorView.visibility = View.GONE
    }

    private fun hideLoadingView() {
        loadingView.visibility = View.GONE
    }

    private fun hideDataView() {
        swipeRefreshLayout.visibility = View.GONE
    }
}

