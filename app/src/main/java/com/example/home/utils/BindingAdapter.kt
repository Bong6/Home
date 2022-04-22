package com.example.home.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-27 21:39
*/


//设置正常图片
@BindingAdapter("setImageSrcNormal")
fun setImageSrcNormal(imageView: ImageView,url : Int){
    Glide.with(imageView).load(url).centerCrop().into(imageView)
}

//设置圆形图片
@BindingAdapter("setImageSrcCircle")
fun setImageSrcCircle(imageView: ImageView,url : Int){
    Glide.with(imageView).load(url).centerCrop().apply(RequestOptions.bitmapTransform(CircleCrop())).into(imageView)
}

