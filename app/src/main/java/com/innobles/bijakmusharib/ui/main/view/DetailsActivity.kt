package com.innobles.bijakmusharib.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.innobles.bijakmusharib.R
import com.innobles.bijakmusharib.databinding.DetailsFragmentBinding
import com.innobles.bijakmusharib.databinding.MainActivityBinding
import com.innobles.bijakmusharib.networkcall.module.NewsFeedResponse

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: DetailsFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.details_fragment)
        binding.item = intent.extras?.getParcelable("Article")?: null as NewsFeedResponse.Article
    }
}