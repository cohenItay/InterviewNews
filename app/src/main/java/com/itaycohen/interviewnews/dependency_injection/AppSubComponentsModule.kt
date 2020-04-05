package com.itaycohen.interviewnews.dependency_injection

import com.itaycohen.interviewnews.ui_layer.articles.ArticlesSubComponent
import dagger.Module

@Module(subcomponents = [ArticlesSubComponent::class])
class AppSubComponentsModule