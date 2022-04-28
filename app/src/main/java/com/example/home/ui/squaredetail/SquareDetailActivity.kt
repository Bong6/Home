package com.example.home.ui.squaredetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.home.R
import com.example.home.adapter.DetailCommentAdapter
import com.example.home.database.entity.Comment
import com.example.home.database.entity.LocalData
import com.example.home.databinding.ActivitySquareDetailBinding


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-23 18:17
*/

class SquareDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySquareDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_square_detail)
        val localData = intent.getBundleExtra("bundle")?.getParcelable<LocalData>("data")
        binding.detailData = localData

        //模拟数据
        val list : MutableList<Comment> = mutableListOf()
        localData?.let {
            if (!localData.answerOne.equals("")){
                val comment1 = Comment(localData.answerOne,localData.answererOne,localData.answerOnePhoto,localData.answerOneTime)
                list.add(comment1)
            }

            if (!localData.answerTwo.equals("")){
                val comment2 = Comment(localData.answerTwo,localData.answererTwo,localData.answerTwoPhoto,localData.answerTwoTime)
                list.add(comment2)
            }
        }

        binding.detailRcv.adapter = DetailCommentAdapter(list)
        binding.detailRcv.itemAnimator = null
        binding.detailRcv.layoutManager = LinearLayoutManager(this)


    }
}