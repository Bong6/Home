package com.example.home.utils

import android.R.attr.scaleHeight
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import jp.wasabeef.glide.transformations.BlurTransformation


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

//给RelativeLayout设置正常图片
@BindingAdapter("setImageSrcNormalForRelativeLayout")
fun setImageSrcNormalForRelativeLayout(relativeLayout: RelativeLayout,url : Int){
    Glide.with(relativeLayout).load(url).centerCrop().into(object : CustomTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            relativeLayout.background = resource
        }

        override fun onLoadCleared(placeholder: Drawable?) {
        }
    })
}



//设置圆形图片
@BindingAdapter("setImageSrcCircle")
fun setImageSrcCircle(imageView: ImageView,url : Int){
    Glide.with(imageView).load(url).centerCrop().apply(RequestOptions.bitmapTransform(CircleCrop())).into(imageView)
}

//设置圆形图片 对Long类型
@BindingAdapter("setImageSrcCircle")
fun setImageSrcCircle(imageView: ImageView,url : Long){
    Glide.with(imageView).load(url.toInt()).centerCrop().apply(RequestOptions.bitmapTransform(CircleCrop())).into(imageView)
}

