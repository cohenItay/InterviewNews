package com.itaycohen.interviewnews.ui_layer.articles

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.itaycohen.interviewnews.R
import com.itaycohen.interviewnews.ui_layer.articles.screen_detailed.ArticleDetailedFragment
import com.itaycohen.interviewnews.ui_layer.articles.screen_detailed.ArticleDetailedViewModel
import com.itaycohen.interviewnews.ui_layer.articles.screen_list.ArticleListFragment
import com.itaycohen.interviewnews.ui_layer.articles.screen_list.ArticleListViewModel
import javax.inject.Inject

/**
 * A custom fragment factory which implement how to instantiate the article related fragments.
 */
class ArticlesFragmentFactory @Inject constructor(
    private val sharedViewModel: ArticlesViewModel,
    private val viewModelFactory: ArticlesViewModelFactory
) : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (loadFragmentClass(classLoader, className)) {
            ArticleListFragment::class.java -> {
                ArticleListFragment(
                    R.layout.fragment_articles_list,
                    viewModelFactory
                )
            }
            ArticleDetailedFragment::class.java -> {
                ArticleDetailedFragment(
                    R.layout.fragment_article_detailed,
                    viewModelFactory
                )
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }

    }
}