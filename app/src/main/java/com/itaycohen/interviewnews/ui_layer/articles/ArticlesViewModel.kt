package com.itaycohen.interviewnews.ui_layer.articles

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import androidx.test.espresso.IdlingResource
import com.itaycohen.interviewnews.data_layer.articles.ArticlesRepository
import com.itaycohen.interviewnews.data_layer.network.QueryState
import com.itaycohen.interviewnews.ui_layer.articles.idling_resources.SimpleIdleResource
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
     * A delegate for checking if lazy is instantiated
     */
    private val idleResourceDelegate = lazy { SimpleIdleResource() }

    /**
    * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
    */
    @VisibleForTesting
    val idleResource: SimpleIdleResource by idleResourceDelegate


    /**
     * True if query is running
     */
    val queryStateLiveData: LiveData<QueryState> = MutableLiveData(QueryState.IDLE)


    private val isEspressoTestRunning: Boolean
        get() = idleResourceDelegate.isInitialized()


    fun updateTopHeadlines() {
        if (queryStateLiveData.value!!.state == QueryState.STATE_RUNNING)
            return

        val mutableQueryLiveData = queryStateLiveData as MutableLiveData
        viewModelScope.launch {
            mutableQueryLiveData.value = QueryState.LOADING
            try {
                if (isEspressoTestRunning)
                    idleResource.setIdle(false)
                repo.updateTopHeadlines()
            } catch (error: Throwable) {
                mutableQueryLiveData.value = QueryState.error(error)
            } finally {
                mutableQueryLiveData.value = QueryState.LOADED
                if (isEspressoTestRunning)
                    idleResource.setIdle(true)
            }
        }
    }
}
