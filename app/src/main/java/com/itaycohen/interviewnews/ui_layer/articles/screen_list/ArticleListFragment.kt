package com.itaycohen.interviewnews.ui_layer.articles.screen_list

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.itaycohen.interviewnews.data_layer.articles.models.TopHeadlinesModel
import com.itaycohen.interviewnews.data_layer.network.QueryState
import com.itaycohen.interviewnews.ui_layer.articles.ArticleViewModel
import com.itaycohen.interviewnews.ui_layer.articles.ArticlesViewModelFactory
import kotlinx.android.synthetic.main.fragment_articles_list.*

class ArticleListFragment (
    @LayoutRes layoutRes: Int,
    private val viewModelFactory: ArticlesViewModelFactory
) : Fragment(layoutRes) {

    private val sharedViewModel: ArticleViewModel by viewModels({activity!!}, { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.topHeadlinesLiveData.observe(this, onHeadlineUpdated)
        sharedViewModel.queryStateLiveData.observe(this, onQueryStateUpdated)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadProgressBar.hide()
        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ArticleListAdapter()
        }
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.updateTopHeadlines()
    }

    private val onHeadlineUpdated = Observer { topHeadlines: TopHeadlinesModel ->
        (recycler.adapter as ArticleListAdapter).apply {
            headlines = topHeadlines
            notifyDataSetChanged()
        }
    }

    private val onQueryStateUpdated = Observer { queryState: QueryState ->
        updateProgressBar(queryState)
        when (queryState.state){
            QueryState.STATE_FAILED -> {
                queryState.err?.let { error ->
                    Snackbar.make(view!!, error.message ?: "Unknown error", Snackbar.LENGTH_LONG)
                }
            }
        }
    }

    private fun updateProgressBar(queryState: QueryState) {
        when (queryState.state) {
            QueryState.STATE_RUNNING -> loadProgressBar.show()
            else -> loadProgressBar.hide()
        }
    }
}