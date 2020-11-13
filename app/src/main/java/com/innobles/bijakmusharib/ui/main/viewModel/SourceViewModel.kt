package com.innobles.bijakmusharib.ui.main.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.innobles.bijakmusharib.BuildConfig
import com.innobles.bijakmusharib.myutils.API_KEY
import com.innobles.bijakmusharib.myutils.Resource
import com.innobles.bijakmusharib.networkcall.module.SourcesResponse
import com.innobles.bijakmusharib.networkcall.repository.MySourceRepository

class SourceViewModel @ViewModelInject constructor(private val mySourceRepository: MySourceRepository) :
    ViewModel() {
    private val param = MutableLiveData<HashMap<String, String>>()
    val article: LiveData<Resource<List<SourcesResponse.MySource>>> = param.switchMap {
        mySourceRepository.getMySource(it)
    }

    fun fetchArticle() {
        val p = hashMapOf<String, String>(API_KEY to BuildConfig.SERVER_KEY)
        param.value = p

    }

}