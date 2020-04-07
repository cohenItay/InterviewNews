package com.itaycohen.interviewnews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.itaycohen.interviewnews.data_layer.articles.ArticlesRepository
import com.itaycohen.interviewnews.data_layer.articles.ArticlesWebService
import com.itaycohen.interviewnews.data_layer.articles.models.TopHeadlinesModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

/**
 * This test check the [ArticlesRepository] api.
 * its uses Kotlin coroutines test tools for actually open connection sockets and retrieve a mocked response.
 */
class RepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule() // for Livedata looper

    @Test
    fun whenUpdateHeadlineSuccess_LiveDataIsUpdated() {
        val topHeadlinesMock = mock(TopHeadlinesModel::class.java)
        `when`(topHeadlinesMock.status).thenReturn("OK")
        val webService = mock<ArticlesWebService> {
            onBlocking { getHeadlines("us") } doReturn Response.success(topHeadlinesMock)
        }
        val repo = ArticlesRepository(webService)
        runBlocking {
            repo.updateTopHeadlines("us")
        }
        assertEquals("OK", repo.topHeadlinesLiveData.value!!.status)
    }
}