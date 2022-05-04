package com.example.home.ui.userRelated

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.home.base.BaseFragment
import com.example.home.databinding.FragmentLoginBinding
import com.example.home.databinding.FragmentSquareBinding
import com.example.home.utils.Flag
import jp.wasabeef.glide.transformations.BlurTransformation


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-04 19:31
*/

class LoginFragment : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater,container,false)

//        Glide.with(binding.loginConstraintlayout).load(Flag.bgloginOcean).apply(
//            RequestOptions.bitmapTransform(
//                BlurTransformation(requireContext(),25,3)
//            ))
//            .into(object : CustomTarget<Drawable>() {
//                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
//                    binding.loginConstraintlayout.background = resource
//                    Log.d("javed","111")
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {
//                    Log.d("javed","222")
//
//                }
//
//            })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setMainScrollToolBar(0)
    }


}