package com.itaycohen.interviewnews.ui_layer.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itaycohen.interviewnews.ui_layer.articles.screen_detailed.ArticleDetailedViewModel
import com.itaycohen.interviewnews.ui_layer.articles.screen_list.ArticleListViewModel
import javax.inject.Inject
import javax.inject.Provider

/**
 * article related view model factory.
 * This class is suitable to wrk with dagger because of the [Provider] encapsulation,
 * dagger will create a Provider pattern implementation for the required viewModels.
 */
class ArticlesViewModelFactory @Inject constructor(
    private val sharedProvider: Provider<ArticlesViewModel>,
    private val listProvider: Provider<ArticleListViewModel>,
    private val detailedProvider: Provider<ArticleDetailedViewModel>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            ArticlesViewModel::class.java -> {
                sharedProvider.get()
            }
            ArticleListViewModel::class.java -> {
                listProvider.get()
            }
            ArticleDetailedViewModel::class.java -> {
                detailedProvider.get()
            }
            else -> {
                TODO("Missing viewModel $modelClass")
            }
        } as T

}