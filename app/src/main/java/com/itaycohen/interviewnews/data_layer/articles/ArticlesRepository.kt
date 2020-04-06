package com.itaycohen.interviewnews.data_layer.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itaycohen.interviewnews.application.di.ActivityScope
import com.itaycohen.interviewnews.data_layer.articles.errors.TopHeadlinesError
import com.itaycohen.interviewnews.data_layer.articles.models.TopHeadlinesModel
import com.itaycohen.interviewnews.data_layer.network.NetworkError
import javax.inject.Inject


/**
 * A repository pattern which manages articles related data fetching.
 */
@ActivityScope
class ArticlesRepository @Inject constructor(
    private val articlesWebService: ArticlesWebService
) {

    /**
     * LiveData holding the updated top headlines
     */
    val topHeadlinesLiveData: LiveData<TopHeadlinesModel> = MutableLiveData()

    /**
     * updates the top headlines.
     * @param forCountry country of required headlines
     * @throws [Throwable] [NetworkError] or [TopHeadlinesError]
     */
    suspend fun updateTopHeadlines(forCountry: String = ISRAERL) {
        // Retrofit 2.6.+ support main-safe function
        // also, it has its own custom Dispatcher, meaning Dispatcher.IO is not being used.
        // so, his api function implementation are main-safe,
        // therefor no need wrap the try catch with witchContext(Dispatcher.IO){}

        val response = try {
            articlesWebService.getHeadlines(forCountry)
        } catch (cause: Throwable) {
            throw NetworkError(cause)
        }

        if (response.isSuccessful) {
            response.body()?.let { topHeadlines ->
                (topHeadlinesLiveData as MutableLiveData).postValue(topHeadlines)
            }
        } else {
            throw TopHeadlinesError("Server ${response.code()} error code. ${response.message()}")
        }
    }

    companion object {
        const val ISRAERL = "il"
    }

}