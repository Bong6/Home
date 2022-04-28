package com.example.home.base

import androidx.fragment.app.Fragment
import com.example.home.MainActivity


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-28 0:08
*/

open class BaseFragment : Fragment() {

    fun setMainScrollToolBar(type : Int){
        val mainActivity = requireContext() as MainActivity
        mainActivity.setScrollToolBar(type)
    }

}