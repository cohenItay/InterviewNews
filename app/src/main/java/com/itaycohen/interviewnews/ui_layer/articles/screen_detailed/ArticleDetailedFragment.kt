package com.itaycohen.interviewnews.ui_layer.articles.screen_detailed

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.itaycohen.interviewnews.data_layer.articles.models.TopHeadlinesModel
import com.itaycohen.interviewnews.ui_layer.articles.ArticleViewModel
import com.itaycohen.interviewnews.ui_layer.articles.ArticlesViewModelFactory
import kotlinx.android.synthetic.main.fragment_article_detailed.*

class ArticleDetailedFragment (
    @LayoutRes layoutRes: Int,
    private val viewModelFactory: ArticlesViewModelFactory
) : Fragment(layoutRes) {

    private val sharedViewModel: ArticleViewModel by viewModels({activity!!}, { viewModelFactory })
    private val safeArgs: ArticleDetailedFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.topHeadlinesLiveData.observe(this, onHeadlineUpdated)
    }

    private val onHeadlineUpdated = Observer { topHeadlines: TopHeadlinesModel ->
        topHeadlines.articlesList[safeArgs.itemPosition].let {
            webview.loadUrl(it.urlToSourceArticle)
        }
    }
}