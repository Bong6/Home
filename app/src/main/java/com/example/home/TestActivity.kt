package com.example.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.home.databinding.FragmentLoginBinding
import com.example.home.utils.Flag
import jp.wasabeef.glide.transformations.BlurTransformation


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-05 0:21
*/

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : FragmentLoginBinding = DataBindingUtil.setContentView(this,R.layout.fragment_login)

        Glide.with(binding.loginRelativelayout).load(Flag.bgloginOcean)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    binding.loginRelativelayout.background = resource
                    Log.d("javed","111")
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    Log.d("javed","222")
                }
            })
    }
}