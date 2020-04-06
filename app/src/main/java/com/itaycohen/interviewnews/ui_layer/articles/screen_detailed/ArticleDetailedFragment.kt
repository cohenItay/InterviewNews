package com.itaycohen.interviewnews.ui_layer.articles.screen_detailed

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.itaycohen.interviewnews.ui_layer.articles.ArticlesViewModelFactory

class ArticleDetailedFragment (
    @LayoutRes layoutRes: Int,
    private val viewModelFactory: ArticlesViewModelFactory
) : Fragment(layoutRes) {

}