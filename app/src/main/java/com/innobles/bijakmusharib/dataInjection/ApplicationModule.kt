package com.innobles.bijakmusharib.dataInjection

import android.content.Context
import com.innobles.bijakmusharib.BuildConfig
import com.innobles.bijakmusharib.networkcall.api.ApiHelperImpl
import com.innobles.bijakmusharib.networkcall.api.ApiService
import com.innobles.bijakmusharib.networkcall.dao.AppDatabase
import com.innobles.bijakmusharib.networkcall.dao.ArticleDao
import com.innobles.bijakmusharib.networkcall.dao.MySourceDao
import com.innobles.bijakmusharib.networkcall.repository.MainRepository
import com.innobles.bijakmusharib.networkcall.repository.MySourceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Musharib Ali on 8/11/20.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL


    @Provides
    @Singleton
    fun provideOkHttp() = run {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(helper: ApiService) = ApiHelperImpl(helper)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideArticleDao(db: AppDatabase) = db.articleDao()

    @Singleton
    @Provides
    fun provideMySourceDao(db: AppDatabase) = db.mySourceDao()

    @Singleton
    @Provides
    fun provideRepository(
        helper: ApiHelperImpl,
        articleDao: ArticleDao
    ) =
        MainRepository(helper, articleDao)


    @Singleton
    @Provides
    fun provideSourceRepository(
        helper: ApiHelperImpl,
        mySourceDao: MySourceDao
    ) =
        MySourceRepository(helper, mySourceDao)

}