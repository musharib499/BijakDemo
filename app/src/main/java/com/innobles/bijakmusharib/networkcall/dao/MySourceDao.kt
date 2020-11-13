package com.innobles.bijakmusharib.networkcall.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.innobles.bijakmusharib.networkcall.module.SourcesResponse

/**
 * Created by Musharib Ali on 12/11/20.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@Dao
interface MySourceDao {
    @Query("SELECT * FROM mysource")
    fun loadAllMySource(): LiveData<List<SourcesResponse.MySource>>

    @Query("SELECT * FROM mysource WHERE mID = :id")
    fun getMySource(id: Int): LiveData<SourcesResponse.MySource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMySource(article: SourcesResponse.MySource)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setAllMySource(article: List<SourcesResponse.MySource>)

}