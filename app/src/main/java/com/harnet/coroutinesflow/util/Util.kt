package com.harnet.coroutinesflow.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.harnet.coroutinesflow.R

//little loading spinner for image loading
fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

//extension for auto loading image of ImageView element using Glide library
fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.tools_placeholder)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)// this - extended ImageView class
}

// load images with Glide library
@BindingAdapter("android:bindImageUrl")
fun loadBindingImage(view: ImageView, url: String?) {
    view.loadImage(url, getProgressDrawable(view.context))
}