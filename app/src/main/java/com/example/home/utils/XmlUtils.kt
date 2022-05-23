package com.example.home.utils

import android.view.View
import android.widget.TextView


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-22 22:56
*/

object XmlUtils {

    //设置CoordinatorLayout高度
    fun setCoordinatorLayoutHeight(mIsMain: Boolean) : Int {
        if (mIsMain){
            return 1340
        }else{
            return 1520
        }
    }

    /*
    设置是否可见
    true 可见
    false 不可见
    */
    fun setVisibility(mFlag: Boolean) : Int {
        if (mFlag){
            return View.VISIBLE
        }else{
            return View.GONE
        }
    }



    //设置是否沉浸式标题栏
    fun setFitsSystemWindows(mIsMain: Boolean) : Boolean {
        return mIsMain
    }


}