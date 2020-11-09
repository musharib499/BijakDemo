package com.innobles.bijakmusharib.networkcall.api

import com.innobles.bijakmusharib.networkcall.module.NewsFeedResponse
import com.innobles.bijakmusharib.networkcall.module.SourcesResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Musharib Ali on 8/11/20.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getArticle(param: HashMap<String, String>): Response<NewsFeedResponse> =
        apiService.getArticle(param)

    override suspend fun getArticleSource(param: HashMap<String, String>): Response<SourcesResponse> =
        apiService.getArticleSource(param)

}