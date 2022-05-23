package com.example.home.ui.userRelated

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.home.R
import com.example.home.databinding.ActivityUserrelatedBinding
import com.example.home.ui.personal.PersonalViewModel
import com.example.home.ui.personal.PersonalViewModelFactory
import com.example.home.utils.Flag
import com.example.home.utils.InjectorUtils
import kotlin.concurrent.thread


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-06 19:01
*/

class UserRelatedActivity : AppCompatActivity() {
    /*
    区分当前是登录页面还是注册页面
    0   ->  登录
    1   ->  注册
    */
    private var xmlType : Boolean = false
    lateinit var viewModel : PersonalViewModel
    private lateinit var binding : ActivityUserrelatedBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_userrelated)
        binding.type = xmlType

        val factory : PersonalViewModelFactory = InjectorUtils.providePersonalViewModelFactory(this)
        viewModel = ViewModelProvider(this,factory).get(PersonalViewModel::class.java)

        //登录或注册
        binding.relatedBtn.setOnClickListener {
            when(xmlType){
                //注册
                true    ->  {

                }

                //登录
                false   ->{
                    thread {
                        val user = viewModel.loginUser(binding.relatedAccount.text.toString(),binding.relatedPassword.text.toString())
                        if (user != null){
                            runOnUiThread {
                                Flag.user = user
                                Flag.userId = user.userId
                            }
                            finish()
                        }else{
                            runOnUiThread {
                                Toast.makeText(this,"账号或密码错误", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

        //切换页面
        binding.relatedChange.setOnClickListener {
            val intent = Intent(this,UserRelatedActivity::class.java)
            val bundle = Bundle()
            bundle.putBoolean("xmlType",!xmlType)
            intent.putExtra("bundle",bundle)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val type = intent!!.getBundleExtra("bundle")!!.getBoolean("xmlType")
        binding.type = type
        xmlType = type
    }
}