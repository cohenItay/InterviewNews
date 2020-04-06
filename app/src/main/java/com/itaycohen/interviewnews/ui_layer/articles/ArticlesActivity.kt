package com.itaycohen.interviewnews.ui_layer.articles

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.itaycohen.interviewnews.R
import com.itaycohen.interviewnews.application.NewsApp
import com.itaycohen.interviewnews.application.di.ActivityScope

@ActivityScope
class ArticlesActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.homeDestination) as NavHostFragment
        host.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val articlesComponent =
            (application as NewsApp).appComponent.getArticlesComponent().create()
        supportFragmentManager.fragmentFactory = articlesComponent.getFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        setupActionBarWithNavController(navController, AppBarConfiguration(navController.graph))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
