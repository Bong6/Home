package com.example.home.utils

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.home.R
import jp.wasabeef.glide.transformations.BlurTransformation


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-27 21:39
*/


//设置正常图片 对String
@BindingAdapter("setImageSrcNormal")
fun setImageSrcNormal(imageView: ImageView,url : String?){
    if(url != null){
        Glide.with(imageView).load(url).centerCrop().error(R.mipmap.image_localdata_error).placeholder(R.mipmap.image_localdata_default).into(imageView)
    }
}

//设置正常图片 对Int
@BindingAdapter("setImageSrcNormal")
fun setImageSrcNormal(imageView: ImageView,url : Int){

    Glide.with(imageView)
        .load(url)
        .error(R.mipmap.image_localdata_error)
        .placeholder(R.mipmap.image_localdata_default)
        .centerCrop()
        .into(imageView)

}

//给RelativeLayout设置背景图
@BindingAdapter("setImageSrcNormalForRelativeLayout")
fun setImageSrcNormalForRelativeLayout(relativeLayout: RelativeLayout,url : Int){
    Glide.with(relativeLayout).load(url).centerCrop()
        .into(object : CustomTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            relativeLayout.background = resource
        }

        override fun onLoadCleared(placeholder: Drawable?) {
        }
    })
}

//给RelativeLayout设置高斯迷糊背景图
@BindingAdapter("setImageSrcNormalForRelativeLayoutAndVague")
fun setImageSrcNormalForRelativeLayoutAndVague(relativeLayout: RelativeLayout,url : Int){
    Glide.with(relativeLayout).load(url).apply(RequestOptions
        .bitmapTransform(BlurTransformation(2)))
        .into(object : CustomTarget<Drawable>() {
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

//设置圆形图片 对String
@BindingAdapter("setImageSrcCircle")
fun setImageSrcCircle(imageView: ImageView,url : String?){
    if (url != null){
        Glide.with(imageView).load(url).centerCrop().apply(RequestOptions.bitmapTransform(CircleCrop())).into(imageView)
    }else{
        Glide.with(imageView).load(R.drawable.icon_personal_defaultphoto).centerCrop().apply(RequestOptions.bitmapTransform(CircleCrop())).into(imageView)
    }
}

