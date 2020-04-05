package com.itaycohen.interviewnews.data_layer.articles

import javax.inject.Inject

/**
 * A repository pattern which manages articles related data fetching.
 */
class ArticlesRepository @Inject constructor(
    networkManager: NetworkManager,
    articlesWebService: ArticlesWebService
) {

}