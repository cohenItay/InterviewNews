package com.itaycohen.interviewnews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.itaycohen.interviewnews.application.di.DaggerAppComponent
import com.itaycohen.interviewnews.data_layer.articles.models.TopHeadlinesModel
import com.itaycohen.interviewnews.ui_layer.articles.ArticlesViewModel
import com.itaycohen.interviewnews.ui_layer.articles.screen_list.ArticleListAdapter
import com.itaycohen.interviewnews.ui_layer.articles.screen_list.ArticleListFragment
import junit.framework.Assert.assertEquals
import org.hamcrest.Matchers.anything


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * This Test will wait until data has loaded from web, click the first article
 * and check that the Detailed fragment is visible.
 */
@RunWith(AndroidJUnit4::class)
class NavigateToDetailedFragmentTest {

    private lateinit var navController: NavController
    private lateinit var topHeadlinesLiveData: LiveData<TopHeadlinesModel>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun initNavTest() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        navController = TestNavHostController(context)
        navController.setGraph(R.navigation.main_nav_graph)

        val fragmentFactory = DaggerAppComponent.factory()
            .create(context)
            .getArticlesComponent()
            .create()
            .getFragmentFactory()

        val fragScenario = launchFragmentInContainer<ArticleListFragment>(factory = fragmentFactory)
        fragScenario.onFragment { listFrag ->
            Navigation.setViewNavController(listFrag.requireView(), navController)
            val sharedViewModel = ViewModelProvider(listFrag.requireActivity()).get(ArticlesViewModel::class.java)
            topHeadlinesLiveData = sharedViewModel.topHeadlinesLiveData
        }
    }

    @Test
    fun startTest() {
        topHeadlinesLiveData.getOrAwaitValue() // wait for data to arrive or throw

        /*onData(anything())
            .inAdapterView(withId(R.id.recycler))
            .atPosition(0)
            .perform(click())*/

        onView(withId(R.id.recycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ArticleListAdapter.ViewHolder>(0, click()))

        assertEquals(navController.currentDestination?.id, R.id.articleDetailedFragment)
    }

    /**
     * If the LiveData already has a value, it returns it immediately.
     * Additionally, if the value is never set,
     * it will throw an exception after 2 seconds (or whatever you set).
     * This prevents tests that never finish when something goes wrong.
     */
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}
