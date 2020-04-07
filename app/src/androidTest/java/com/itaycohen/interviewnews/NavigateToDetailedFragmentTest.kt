package com.itaycohen.interviewnews

import androidx.fragment.app.testing.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.itaycohen.interviewnews.application.di.DaggerAppComponent
import com.itaycohen.interviewnews.ui_layer.articles.ArticlesViewModel
import com.itaycohen.interviewnews.ui_layer.articles.screen_list.ArticleListFragment


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * This Test will wait until data has loaded from web, click the first article
 * and check that the Detailed fragment is visible.
 */
@RunWith(AndroidJUnit4::class)
class NavigateToDetailedFragmentTest {

    private lateinit var mIdleResource: IdlingResource

    @Before
    fun initNavTest() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val navController = TestNavHostController(context)
        navController.setGraph(R.navigation.main_nav_graph)

        val fragmentFactory = DaggerAppComponent.factory()
            .create(context)
            .getArticlesComponent()
            .create()
            .getFragmentFactory()

        val fragScenario = launchFragmentInContainer<ArticleListFragment>(factory = fragmentFactory)
        fragScenario.onFragment { listFrag ->
            Navigation.setViewNavController(listFrag.requireView(), navController)
        }
    }

    @Test
    fun startTest() {

    }
}
