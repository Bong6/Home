package com.example.home.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.home.R
import com.example.home.databinding.ActivityTest2Binding


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-22 11:42
*/

class TestActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityTest2Binding = DataBindingUtil.setContentView(this, R.layout.activity_test2)

        val list = mutableListOf<String>()
        for (a in 0..100){
            list.add("javed")
        }


        binding.recy2.adapter = Test2Adapter(list)
        binding.recy2.layoutManager = LinearLayoutManager(this)



    }
}