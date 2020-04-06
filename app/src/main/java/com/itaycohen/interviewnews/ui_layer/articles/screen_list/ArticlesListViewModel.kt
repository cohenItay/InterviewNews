package com.itaycohen.interviewnews.ui_layer.articles.screen_list

import androidx.lifecycle.ViewModel
import com.itaycohen.interviewnews.data_layer.articles.ArticlesRepository
import javax.inject.Inject

class ArticlesListViewModel @Inject constructor(
    private val repo: ArticlesRepository
): ViewModel() {

    /* This space is for rent =] just wanted to show the possibility to retrieve unique viewmodel for fragment */
}