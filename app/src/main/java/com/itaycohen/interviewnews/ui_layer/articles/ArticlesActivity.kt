package com.itaycohen.interviewnews.ui_layer.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.itaycohen.interviewnews.R
import com.itaycohen.interviewnews.application.NewsApp
import com.itaycohen.interviewnews.application.di.ActivityScope
import com.itaycohen.interviewnews.ui_layer.articles.di.ArticlesComponent
import javax.inject.Inject

@ActivityScope
class ArticlesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val articlesComponent = (application as NewsApp).appComponent.getArticlesComponent().create()
        supportFragmentManager.fragmentFactory = articlesComponent.getFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.homeDestination) as NavHostFragment
        val navController = host.navController
        setupActionBarWithNavController(navController, AppBarConfiguration(navController.graph))
    }
}
