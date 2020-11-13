package com.innobles.bijakmusharib.networkcall.api

import javax.inject.Inject

/**
 * Created by Musharib Ali on 8/11/20.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : BaseDataSource() {
    suspend fun getArticle(param: HashMap<String, String>) = getResult {
        apiService.getArticle(param)
    }

    suspend fun getArticleSource(param: HashMap<String, String>) = getResult {
        apiService.getArticleSource(param)
    }

}