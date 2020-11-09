package com.innobles.bijakmusharib.ui.main.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.innobles.bijakmusharib.BuildConfig
import com.innobles.bijakmusharib.myutils.*
import com.innobles.bijakmusharib.networkcall.module.NewsFeedResponse
import com.innobles.bijakmusharib.networkcall.module.SourcesResponse
import com.innobles.bijakmusharib.networkcall.repository.MainRepository
import kotlinx.coroutines.launch

class SourceViewModel @ViewModelInject constructor(private val mainRepository: MainRepository, private val utils: Utils) : ViewModel(){

    private val mArticle = MutableLiveData<Resource<SourcesResponse>>()
    val article: LiveData<Resource<SourcesResponse>> get() =  mArticle

    init {
        fetchArticle()
    }

    fun fetchArticle(country:String = "us", category:String = "",search:String = "") {
        val param = hashMapOf<String,String>(API_KEY to BuildConfig.SERVER_KEY)
        viewModelScope.launch {
            mArticle.postValue(Resource.loading(null))
            if (utils.isNetworkConnected()) {
                mainRepository.getArticleSource(param).let {
                    if (it.isSuccessful) {
                        if (it.body()?.status == "ok") {
                            mArticle.postValue(Resource.success(it.body()))
                        }else{
                            mArticle.postValue(Resource.error(it.body()?.status?:"", null))
                        }
                    } else {
                        mArticle.postValue(Resource.error(it.errorBody().toString(), null))
                    }

                }
            } else
                mArticle.postValue(Resource.error("No internet connection", null)
                )
        }
    }



}