package com.itaycohen.interviewnews.ui_layer.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itaycohen.interviewnews.R
import com.itaycohen.interviewnews.application.NewsApp
import com.itaycohen.interviewnews.application.di.ActivityScope
import com.itaycohen.interviewnews.application.di.DaggerAppComponent
import com.itaycohen.interviewnews.ui_layer.articles.di.ArticlesComponent

@ActivityScope
class ArticlesActivity : AppCompatActivity() {

    lateinit var articlesComponent: ArticlesComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        articlesComponent = (application as NewsApp).appComponent.getArticlesComponent().create()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)
    }
}
