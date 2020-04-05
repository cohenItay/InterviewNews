package com.itaycohen.interviewnews.dependency_injection

import android.content.Context
import com.itaycohen.interviewnews.ui_layer.articles.ArticlesSubComponent
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AppSubComponentsModule::class])
interface AppComponent {

    fun articlesSubComponentFactory() : ArticlesSubComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }
}