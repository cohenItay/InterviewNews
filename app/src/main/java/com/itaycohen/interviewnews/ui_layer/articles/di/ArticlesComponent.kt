package com.itaycohen.interviewnews.ui_layer.articles.di

import com.itaycohen.interviewnews.application.di.ActivityScope
import com.itaycohen.interviewnews.data_layer.articles.di.ArticlesNetworkModule
import com.itaycohen.interviewnews.ui_layer.articles.ArticleDetailedFragment
import com.itaycohen.interviewnews.ui_layer.articles.ArticleListFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ArticlesNetworkModule::class])
interface ArticlesComponent {

    fun injectTo(fragment: ArticleListFragment)
    fun injectTo(fragment: ArticleDetailedFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create() : ArticlesComponent
    }
}