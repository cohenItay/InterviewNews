package com.itaycohen.interviewnews.ui_layer.articles.screen_detailed

import androidx.lifecycle.ViewModel
import com.itaycohen.interviewnews.data_layer.articles.ArticlesRepository
import javax.inject.Inject

class ArticleDetailedViewModel @Inject constructor(
    private val repo: ArticlesRepository
): ViewModel() {

}
