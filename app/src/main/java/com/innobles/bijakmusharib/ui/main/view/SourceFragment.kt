package com.innobles.bijakmusharib.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.innobles.bijakmusharib.R
import com.innobles.bijakmusharib.databinding.ItemSourcesBinding
import com.innobles.bijakmusharib.databinding.MainSourceFragmentBinding
import com.innobles.bijakmusharib.myutils.Status
import com.innobles.bijakmusharib.networkcall.module.SourcesResponse
import com.innobles.bijakmusharib.ui.main.view.adapter.BaseAdapterBinding
import com.innobles.bijakmusharib.ui.main.viewModel.SourceViewModel


class SourceFragment : Fragment(), BaseAdapterBinding.BindAdapterListener {

    companion object {
        fun newInstance() = SourceFragment()
    }

    private lateinit var viewModel: SourceViewModel
    private lateinit var binding: MainSourceFragmentBinding
    private lateinit var baseAdapterBinding: BaseAdapterBinding<SourcesResponse.MySource?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainSourceFragmentBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    private fun setUp(){
        viewModel = activity?.let { ViewModelProvider(it).get(SourceViewModel::class.java) }!!
        viewModel.fetchArticle()
        setLiveData()
    }

    private fun setLiveData() {
        baseAdapterBinding = BaseAdapterBinding(
            activity?.baseContext!!,
            arrayListOf(),
            this,
            R.layout.item_sources
        )
       with(binding){
           this.recyclerView.adapter = baseAdapterBinding
           viewModel.article.observe(viewLifecycleOwner, {
               when (it.status) {

                   Status.LOADING -> {
                       this.loading = true
                   }
                   Status.SUCCESS -> {
                       baseAdapterBinding.setData(it.data)
                       baseAdapterBinding.notifyDataSetChanged()
                       this.error = false

                       this.loading = false

                   }
                   Status.ERROR -> {
                       this.loading = false
                       this.error = true
                       this.message = it.message
                   }
               }
           })
       }
    }

    override fun onBind(holder: BaseAdapterBinding.DataBindingViewHolder, position: Int) {
        with(holder.binding) {
            if (this is ItemSourcesBinding){
                this.item = baseAdapterBinding.getItem(position)
            }
        }
    }


}