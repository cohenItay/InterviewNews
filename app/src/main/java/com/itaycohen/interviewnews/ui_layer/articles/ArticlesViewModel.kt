package com.itaycohen.interviewnews.ui_layer.articles

import androidx.lifecycle.*
import com.itaycohen.interviewnews.data_layer.articles.ArticlesRepository
import com.itaycohen.interviewnews.data_layer.articles.models.TopHeadlinesModel
import com.itaycohen.interviewnews.data_layer.network.QueryState
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This ViewModel is intended to share data between fragment.
 * should be instantiated using [ArticlesActivity] as [ViewModelStoreOwner]
 */
class ArticlesViewModel @Inject constructor(
    private val repo: ArticlesRepository
) : ViewModel() {


    // each time repo live data is updated so does this live data
    val topHeadlinesLiveData = Transformations.map(repo.topHeadlinesLiveData) {it}

    /**
     * Live data object holding the [TopHeadlinesModel] query status
     */
    val queryStateLiveData: LiveData<QueryState> = MutableLiveData(QueryState.IDLE)



    fun updateTopHeadlines() {
        if (queryStateLiveData.value!!.state == QueryState.STATE_RUNNING)
            return

        val mutableQueryLiveData = queryStateLiveData as MutableLiveData
        viewModelScope.launch {
            mutableQueryLiveData.value = QueryState.LOADING
            try {
                repo.updateTopHeadlines()
            } catch (error: Throwable) {
                mutableQueryLiveData.value = QueryState.error(error)
            } finally {
                mutableQueryLiveData.value = QueryState.LOADED
            }
        }
    }
}
