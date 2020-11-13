package com.innobles.bijakmusharib.networkcall.repository

import com.innobles.bijakmusharib.myutils.performGetOperation
import com.innobles.bijakmusharib.networkcall.api.ApiHelperImpl
import com.innobles.bijakmusharib.networkcall.dao.MySourceDao
import com.innobles.bijakmusharib.networkcall.module.SourcesResponse
import javax.inject.Inject

/**
 * Created by Musharib Ali on 8/11/20.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class MySourceRepository @Inject constructor(
    private val apiHelper: ApiHelperImpl,
    private val mySourceDao: MySourceDao
) {
    fun getMySource(param: HashMap<String, String>) = performGetOperation(
        databaseQuery = { mySourceDao.loadAllMySource() },
        networkCall = { apiHelper.getArticleSource(param) },
        saveCallResult = {
            it.sources?.let {
                mySourceDao.setAllMySource(it as List<SourcesResponse.MySource>)
            }


        }


    )


}