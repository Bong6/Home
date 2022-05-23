package com.example.home.ui.encyclopediasdetail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.home.R
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.LocalData
import com.example.home.database.entity.UserCollectionAndPublish
import com.example.home.databinding.ActivityEncyclopediasDetailBinding
import com.example.home.ui.suqare.SquareViewModel
import com.example.home.ui.suqare.SquareViewModelFactory
import com.example.home.utils.Flag
import com.example.home.utils.InjectorUtils
import kotlin.concurrent.thread


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-27 16:32
*/

class EncyclopediasDetailActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var viewModel : EncyclopediasDetailViewModel
    private var knowledge : EncyclopediaKnowledge ?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityEncyclopediasDetailBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_encyclopedias_detail)

        knowledge = intent.getBundleExtra("bundle")?.getParcelable<EncyclopediaKnowledge>("knowledge")
        binding.knowledge = knowledge

        val factory : EncyclopediasDetailViewModelFactory = InjectorUtils.provideEncyclopediasDetailViewModelFactory(this)
        viewModel  = ViewModelProvider(this,factory).get(EncyclopediasDetailViewModel::class.java)

        //查询是否收藏过
        thread {
            viewModel.queryUserCollectionRecord_EncyclopediaKnowledge_Whether(Flag.userId,knowledge!!.encyclopediaknowledge_id)
        }

        viewModel.hasCollected.observe(this,object : Observer<Boolean>{
            override fun onChanged(boolean: Boolean) {
                binding.hasCollected = boolean
            }
        })

        supportActionBar?.title = knowledge?.sickName
        //点击事件
        binding.detailHascollectedImg.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.detail_hascollected_img    ->{

                if(viewModel.hasCollected.value!!){
                    //如果收藏了
                        thread {
                            //1.取消收藏
                            viewModel.collectandpublish_id.value?.let {
                                viewModel.cancelUserCollectionRecord(it)
                                runOnUiThread {
                                    Toast.makeText(this,"取消收藏",Toast.LENGTH_SHORT).show()
                                }
                            }?: Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show()
                            //2.弹窗
                        }

                }else{
                    //如果没收藏
                        thread {
                            //1.收藏
                            val userCollectionAndPublish = UserCollectionAndPublish(
                                2,
                                2,
                                knowledge!!.encyclopediaknowledge_id,
                                Flag.userId
                            )
                            viewModel.insertUserCollectionRecord(userCollectionAndPublish)
                            runOnUiThread {
                                //2.弹窗
                                Toast.makeText(this,"收藏成功",Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }

            else    ->{

            }
        }
    }
}