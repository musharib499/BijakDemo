package com.innobles.bijakmusharib.networkcall.repository

import com.innobles.bijakmusharib.myutils.CATEGORY_KEY
import com.innobles.bijakmusharib.myutils.PAGE
import com.innobles.bijakmusharib.myutils.performGetOperation
import com.innobles.bijakmusharib.networkcall.api.ApiHelperImpl
import com.innobles.bijakmusharib.networkcall.dao.ArticleDao
import com.innobles.bijakmusharib.networkcall.module.Article
import javax.inject.Inject

/**
 * Created by Musharib Ali on 8/11/20.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class MainRepository @Inject constructor(
    private val apiHelper: ApiHelperImpl,
    private val articleDao: ArticleDao
) {
    fun getArticle(param: HashMap<String, String>) = performGetOperation(
        databaseQuery = {
            articleDao.loadAllArticle(
                param[CATEGORY_KEY].toString(),
                param[PAGE]?.toInt() ?: 0
            )
        },
        networkCall = { apiHelper.getArticle(param) },
        saveCallResult = {
            it.articles?.let { list ->
                for (item in list) {
                    item?.category = param[CATEGORY_KEY]
                    item?.page = param[PAGE]?.toInt() ?: 0
                    item?.totalPage = it.totalResults ?: 0
                    item?.let { it1 -> articleDao.setArticle(it1) }
                }
            }

        }


    )

    suspend fun setArticle(article: Article) = articleDao.setArticle(article)
    suspend fun setAllArticle(articleList: List<Article>) =
        articleDao.setAllArticle(articleList)

}