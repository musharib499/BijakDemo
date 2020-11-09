package com.innobles.bijakmusharib.ui.main.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.innobles.bijakmusharib.BuildConfig
import com.innobles.bijakmusharib.databinding.MainFragmentBinding
import com.innobles.bijakmusharib.myutils.*
import com.innobles.bijakmusharib.networkcall.module.NewsFeedResponse
import com.innobles.bijakmusharib.networkcall.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository, private val utils: Utils) : ViewModel(){

    private val mArticle = MutableLiveData<Resource<NewsFeedResponse>>()
    val article: LiveData<Resource<NewsFeedResponse>> get() =  mArticle

 init {
     fetchArticle()
 }

      fun fetchArticle(category:String = "business", page:String = "0") {
        val param = hashMapOf<String,String>(CATEGORY_KEY to category,"page" to page ,API_KEY to BuildConfig.SERVER_KEY)

        viewModelScope.launch {
            mArticle.postValue(Resource.loading(null))
            if (utils.isNetworkConnected()) {
                mainRepository.getArticle(param).let {
                    if (it.isSuccessful) {
                        if (it.body()?.status == "ok") {
                            mArticle.postValue(Resource.success(it.body()))
                        }else{
                            mArticle.postValue(Resource.error(it.body()?.message?:"", null))
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