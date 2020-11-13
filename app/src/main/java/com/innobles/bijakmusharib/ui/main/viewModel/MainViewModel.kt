package com.innobles.bijakmusharib.ui.main.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.innobles.bijakmusharib.BuildConfig
import com.innobles.bijakmusharib.myutils.*
import com.innobles.bijakmusharib.networkcall.module.Article
import com.innobles.bijakmusharib.networkcall.repository.MainRepository

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val utils: Utils
) : ViewModel() {

    private val param = MutableLiveData<HashMap<String, String>>()
    val article: LiveData<Resource<List<Article>>> = param.switchMap {
        mainRepository.getArticle(it)
    }


    fun fetchArticle(category: String = "business", page: String = "0") {
        val p = hashMapOf<String, String>(
            CATEGORY_KEY to category,
            PAGE to page,
            API_KEY to BuildConfig.SERVER_KEY
        )
        param.value = p

    }

}