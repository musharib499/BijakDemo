package com.innobles.bijakmusharib.extention

import android.text.TextUtils
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Created by Musharib Ali on 8/11/20.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */

@BindingAdapter("imageFromUrl")
fun AppCompatImageView.bindImageFromUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(this.context)   // pass Context
            .load(imageUrl)    // pass the image url
            //.placeholder(R.drawable.ic_image_place_holder) // optional placeholder
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(message: String?) {
    this.visibility = if (TextUtils.isEmpty(message)) View.GONE else View.VISIBLE
}

@BindingAdapter("isVisible")
fun View.setIsVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
