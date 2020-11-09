package com.innobles.bijakmusharib.networkcall.api

import com.innobles.bijakmusharib.networkcall.module.NewsFeedResponse
import com.innobles.bijakmusharib.networkcall.module.SourcesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Musharib Ali on 8/11/20.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
interface ApiService {

    @GET("top-headlines")
    suspend fun getArticle(@QueryMap param: HashMap<String, String>): Response<NewsFeedResponse>

    @GET("sources")
    suspend fun getArticleSource(@QueryMap param: HashMap<String, String>): Response<SourcesResponse>
}