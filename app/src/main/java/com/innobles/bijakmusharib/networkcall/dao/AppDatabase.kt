package com.innobles.bijakmusharib.networkcall.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.innobles.bijakmusharib.networkcall.module.Article
import com.innobles.bijakmusharib.networkcall.module.Source
import com.innobles.bijakmusharib.networkcall.module.SourcesResponse

/**
 * Created by Musharib Ali on 12/11/20.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */

@Database(
    entities = [Article::class, Source::class, SourcesResponse.MySource::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
    abstract fun mySourceDao(): MySourceDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "bijak")
                .fallbackToDestructiveMigration()
                .build()
    }

}

