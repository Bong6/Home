package com.example.home.utils

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton


/*
    @Author 姜小龙
    @Description

        广场悬浮按钮的行为
                    ->  下滑隐藏上拉显示

    @Date 2022-03-31 11:38
*/

class FabScrollBehavior(context: Context,attributeSet: AttributeSet) : FloatingActionButton.Behavior(context,attributeSet) {


    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        directTargetChild: View,
        target: View,
        axes: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed
        )

        if (dyConsumed > 0) { // 向下滑动   隐藏
            animateOut(child)
        } else if (dyConsumed < 0) { // 向上滑动    显示
            animateIn(child)
        }
    }

//    override fun onStopNestedScroll(
//        coordinatorLayout: CoordinatorLayout,
//        child: FloatingActionButton,
//        target: View
//    ) {
//        super.onStopNestedScroll(coordinatorLayout, child, target)
//        if (mIsStopScroll){
//            Log.d("javed","onStopNestedScroll")
//            animateIn(child)
//            mIsStopScroll = false
//        }
//    }

    private fun animateIn(child: FloatingActionButton) {
        child.animate().translationY(0F).setInterpolator(LinearInterpolator()).start();
    }

    private fun animateOut(child: FloatingActionButton) {
        val layoutParams = child.layoutParams as CoordinatorLayout.LayoutParams
        val bottomMargin = layoutParams.bottomMargin
        child.animate().translationY((child.getHeight() + bottomMargin).toFloat()).setInterpolator(
            LinearInterpolator()
        ).start()
    }

}