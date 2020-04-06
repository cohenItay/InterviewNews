package com.itaycohen.interviewnews.application.di

import com.itaycohen.interviewnews.ui_layer.articles.di.ArticlesComponent
import dagger.Module

@Module(subcomponents = [ArticlesComponent::class])
class AppSubComponentsModule