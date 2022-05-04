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
import com.example.home.net.entity.AnswerInformation


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
        val list = intent.getBundleExtra("bundle")?.getParcelableArrayList<AnswerInformation>("commentList")


        binding.detailData = localData
        binding.commentAccount = list?.size ?: 0

        //展示评论数据
        binding.detailRcv.adapter = DetailCommentAdapter(list)
        binding.detailRcv.itemAnimator = null
        binding.detailRcv.layoutManager = LinearLayoutManager(this)
    }
}