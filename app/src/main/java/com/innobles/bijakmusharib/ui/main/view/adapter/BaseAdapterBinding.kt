package com.innobles.bijakmusharib.ui.main.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Musharib Ali on 8/11/20.
 * musharib.ali@innobles.com
 */
class BaseAdapterBinding<S>(
    private val mContext: Context,
    objects: List<S>?,
    private val mListener: BindAdapterListener,
    private val layoutId: Int
) : RecyclerView.Adapter<BaseAdapterBinding.DataBindingViewHolder>() {
    private val TYPE_FOOTER: Int = 2
    private val TYPE_ITEM: Int = 1
    private val TYPE_HEADER: Int = 0
    private val TYPE_ERROR: Int = 0
    private var FOOTER: Int = 0
    private var HEADER: Int = 0
    private var ERROR: Int = 0
    private var layoutFooterId: Int = -1
    private var layoutHeaderId: Int = -1
    private var layoutErrorId: Int = -1
    var responseHeader: Any? = null
    var responseFooter: Any? = null
    private var mObjects: MutableList<S>? = ArrayList()

    fun setFooter(layoutFooterId: Int) {
        this.FOOTER = 1
        this.layoutFooterId = layoutFooterId
        this.notifyDataSetChanged()
    }

    fun setFooter(layoutFooterId: Int, response: Any) {
        this.FOOTER = 1
        this.layoutFooterId = layoutFooterId
        this.responseFooter = response
        this.notifyDataSetChanged()
    }

    fun setHeader(layoutHeaderId: Int) {
        this.HEADER = 1
        this.layoutHeaderId = layoutHeaderId
        this.notifyDataSetChanged()
    }

    fun setErrorView(layoutHeaderId: Int) {
        this.ERROR = 1
        this.layoutErrorId = layoutHeaderId
        this.notifyDataSetChanged()
    }

    fun setHeader(layoutHeaderId: Int, response: Any) {
        this.HEADER = 1
        this.layoutHeaderId = layoutHeaderId
        this.responseHeader = response
        this.notifyDataSetChanged()
    }


    fun removeFooter() {
        this.FOOTER = 0
    }

    fun removeHeader() {
        this.HEADER = 0
    }

    fun removeERROR() {
        this.ERROR = 0
    }


//    private val mHolder: T? = null

    val list: List<S>?
        get() = mObjects


    init {
        mObjects = objects as MutableList<S>? ?: mObjects
    }

    fun setData(objects: List<S>?) {
        this.mObjects = objects as MutableList<S>
        this.notifyDataSetChanged()
    }

    fun addAll(objects: List<S>) {
        this.mObjects?.addAll(objects)
        this.notifyDataSetChanged()
    }

    fun add(obj: S) {
        this.mObjects?.add(obj)
        this.notifyDataSetChanged()
    }

    fun addNewItem(obj: S) {
        this.mObjects?.add(obj)
        this.notifyItemInserted(this.getLastItem())
    }

    fun remove(index: Int) {
        this.mObjects?.removeAt(index)
        this.notifyItemRemoved(index)
        //notifyItemRangeRemoved(index,mObjects.size());
//        this.notifyItemChanged(index, mObjects!!.size)

    }

    fun remove(obj: S) {
        this.mObjects?.remove(obj)
        this.notifyDataSetChanged()
    }

    fun getItem(index: Int): S {
        if (mObjects?.size ?: 0 > 0) {
            return mObjects!![index - this.HEADER]
        } else {
            return mObjects?.get(index) ?: null as S
        }
    }


    override fun getItemViewType(position: Int): Int {
        if (isPositionHeader(position)) {
            return this.TYPE_HEADER

        } else if (isPositionFooter(position)) {
            return this.TYPE_FOOTER
        }

        return this.TYPE_ITEM
    }

    override fun getItemCount(): Int {
        // Add two more counts to accomodate header and footer
        return if (mObjects == null) 0 else (mObjects!!.size + this.HEADER + this.FOOTER + this.ERROR)

    }

    fun getTotalListCount(): Int {
        return if (mObjects == null) 0 else mObjects!!.size
    }

    private fun isPositionHeader(position: Int): Boolean {
        return if (this.HEADER == 1) position == 0 else false
    }

    private fun isPositionFooter(position: Int): Boolean {
        return if (this.FOOTER == 1) position == itemCount - 1 else false

    }

    fun getLastItem(): Int {
        return if (mObjects == null) 0 else (mObjects!!.size)
    }

    fun filteredList(objects: List<S>?) {
        this.mObjects = objects as MutableList<S>
        this.notifyDataSetChanged()
    }


    fun refresh() {
        mObjects!!.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)

        if (viewType == this.TYPE_FOOTER) {
            return DataBindingViewHolder(
                DataBindingUtil.inflate(
                    layoutInflater,
                    layoutFooterId,
                    parent,
                    false
                )
            )
//            return mHolderFooterClass?.getConstructor(View::class.java)
//                ?.newInstance(DataBindingUtil.inflate(layoutInflater, layoutFooterId,parent, false))!!
        } else if (viewType == this.TYPE_HEADER) {
            return DataBindingViewHolder(
                DataBindingUtil.inflate(
                    layoutInflater,
                    layoutHeaderId,
                    parent,
                    false
                )
            )
        } else if (viewType == this.TYPE_ITEM) {
            return DataBindingViewHolder(
                DataBindingUtil.inflate(
                    layoutInflater,
                    layoutId,
                    parent,
                    false
                )
            )

        } else if (viewType == this.TYPE_ERROR) {
            return DataBindingViewHolder(
                DataBindingUtil.inflate(
                    layoutInflater,
                    layoutErrorId,
                    parent,
                    false
                )
            )

        } else {
            return DataBindingViewHolder(
                DataBindingUtil.inflate(
                    layoutInflater,
                    layoutId,
                    parent,
                    false
                )
            )

        }


    }


    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        mListener.let {
            it.onBind(holder, position)
        }
    }


    interface BindAdapterListener {
        fun onBind(holder: DataBindingViewHolder, position: Int)
    }

//    interface OnViewRecycledListener {
////        fun onViewRecycled(holder: DataBindingViewHolder)
////    }

    companion object {
        private val TAG = "BaseRecyclerAdapter"
    }

    class DataBindingViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)


}
