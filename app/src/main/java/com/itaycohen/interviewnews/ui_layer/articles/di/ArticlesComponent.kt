package com.itaycohen.interviewnews.ui_layer.articles.di

import androidx.fragment.app.FragmentFactory
import com.itaycohen.interviewnews.application.di.ActivityScope
import com.itaycohen.interviewnews.data_layer.articles.di.ArticlesNetworkModule
import com.itaycohen.interviewnews.ui_layer.articles.ArticlesActivity
import com.itaycohen.interviewnews.ui_layer.articles.screen_detailed.ArticleDetailedFragment
import com.itaycohen.interviewnews.ui_layer.articles.screen_list.ArticleListFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ArticlesNetworkModule::class ,ArticlesUiModule::class])
interface ArticlesComponent {

    fun getFragmentFactory(): FragmentFactory

    @Subcomponent.Factory
    interface Factory {
        fun create() : ArticlesComponent
    }
}