package com.innobles.bijakmusharib.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.innobles.bijakmusharib.R
import com.innobles.bijakmusharib.databinding.ItemFeedNewsBinding
import com.innobles.bijakmusharib.databinding.MainFragmentBinding
import com.innobles.bijakmusharib.myutils.Status
import com.innobles.bijakmusharib.myutils.categoryResponse
import com.innobles.bijakmusharib.networkcall.module.Article
import com.innobles.bijakmusharib.ui.main.view.adapter.BaseAdapterBinding
import com.innobles.bijakmusharib.ui.main.view.adapter.PaginationScrollListener
import com.innobles.bijakmusharib.ui.main.viewModel.MainViewModel


class MainFragment : Fragment(), BaseAdapterBinding.BindAdapterListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private var pageNumber = 0
    private var totalpage = 0
    private var category = "business"
    private val isLastPage = false
    private var isLoading = false
    private lateinit var baseAdapterBinding: BaseAdapterBinding<Article?>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
    }

    private fun setUp() {
        viewModel = activity?.let { ViewModelProvider(it).get(MainViewModel::class.java) }!!
        setUpAdapter()
        setLiveData()
        setCategoryData()
        viewModel.fetchArticle()

    }

   private fun setUpAdapter(){
       baseAdapterBinding = BaseAdapterBinding(
           activity?.baseContext!!,
           arrayListOf(),
           this,
           R.layout.item_feed_news
       )
        with(binding) {
            this.recyclerView.adapter = baseAdapterBinding
            this.recyclerView.addOnScrollListener(object :
                PaginationScrollListener(this.recyclerView.layoutManager as LinearLayoutManager) {
                override fun getTotalPageCount(): Int {
                    return totalpage / 20
                }

                override fun loadMoreItems() {
                    isLoading = true
                    pageNumber += 1
                    viewModel.fetchArticle(category, pageNumber.toString())
                }

                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }
            })
        }
   }


    private fun setLiveData() {
        with(binding){
            viewModel.article.observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        if (!it.data.isNullOrEmpty()) {
                            if (pageNumber == 0) {
                                baseAdapterBinding.setData(it.data)
                                baseAdapterBinding.notifyDataSetChanged()
                            } else {
                                baseAdapterBinding.addAll(it.data)
                            }

                        }
                        this.loading = false
                        isLoading = false

                    }
                    Status.ERROR -> {
                        if ((baseAdapterBinding.list.isNullOrEmpty() && baseAdapterBinding.list?.size ?: 0 > 0)) {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                        isLoading = false
                    }

                    Status.LOADING ->
                        this.loading = true
                }

            })
        }
    }


    @SuppressLint("InflateParams")
    private fun setCategoryData() {
        for (item in categoryResponse.category) {
            val chip: Chip =
                layoutInflater.inflate(R.layout.item_categorie_tag, null, false) as Chip
            with(chip) {
                this.setOnClickListener {
                    item.key?.let {
                        pageNumber = 0
                        category = it
                        viewModel.fetchArticle(category = it, page = pageNumber.toString())

                    }
                }
                this.text = item.value
                binding.chipGroup.addView(this)
            }

        }
    }

    fun moveNext(article: Article) {
        var intent = Intent(activity, DetailsActivity::class.java)
        var b = Bundle()
        b.putParcelable("Article", article)
        intent.putExtras(b)
        startActivity(intent)
    }

    override fun onBind(holder: BaseAdapterBinding.DataBindingViewHolder, position: Int) {
        with(holder.binding) {
            if (this is ItemFeedNewsBinding) {
                baseAdapterBinding.getItem(position)?.let {
                    this.item = it
                    this.frgament = this@MainFragment
                    totalpage = it.totalPage ?: 0
                    Log.d("page", (it?.totalPage ?: 0).toString())
                }

            }
        }
    }
}