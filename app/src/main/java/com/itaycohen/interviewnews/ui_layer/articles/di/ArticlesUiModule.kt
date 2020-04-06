package com.itaycohen.interviewnews.ui_layer.articles.di

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.itaycohen.interviewnews.ui_layer.articles.ArticlesFragmentFactory
import com.itaycohen.interviewnews.ui_layer.articles.ArticlesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArticlesUiModule {

    @Provides
    fun provideFragmentFactory(
        articlesFragmentFactory: ArticlesFragmentFactory
    ) : FragmentFactory = articlesFragmentFactory

    @Provides
    fun provideViewModelFactory(
        articlesViewModelFactory: ArticlesViewModelFactory
    ) : ViewModelProvider.Factory = articlesViewModelFactory
}