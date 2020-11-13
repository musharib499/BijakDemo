package com.innobles.bijakmusharib.networkcall.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.innobles.bijakmusharib.networkcall.module.Article

/**
 * Created by Musharib Ali on 12/11/20.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@Dao
interface ArticleDao {
    @Query("SELECT * FROM article WHERE category = :category AND page = :page")
    fun loadAllArticle(category: String, page: Int): LiveData<List<Article>>

    @Query("SELECT * FROM article WHERE aID = :id")
    fun getArticle(id: Int): LiveData<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setArticle(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setAllArticle(article: List<Article>)

}