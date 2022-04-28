package com.example.home.ui.encyclopediasdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.home.R
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.LocalData
import com.example.home.databinding.ActivityEncyclopediasDetailBinding


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-27 16:32
*/

class EncyclopediasDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityEncyclopediasDetailBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_encyclopedias_detail)

        val knowledge = intent.getBundleExtra("bundle")?.getParcelable<EncyclopediaKnowledge>("knowledge")
        binding.knowledge = knowledge

        supportActionBar?.title = knowledge?.sickName

    }
}