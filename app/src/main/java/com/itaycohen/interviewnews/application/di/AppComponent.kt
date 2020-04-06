package com.itaycohen.interviewnews.application.di

import android.content.Context
import com.itaycohen.interviewnews.ui_layer.articles.di.ArticlesComponent
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AppSubComponentsModule::class])
interface AppComponent {

    fun getArticlesComponent() : ArticlesComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }
}