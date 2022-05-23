package com.example.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.home.databinding.ActivityMainBinding
import com.example.home.ui.userRelated.ArticleActivity
import com.google.android.material.appbar.AppBarLayout


class MainActivity : AppCompatActivity() {

    private val navController: NavController
        get() {
            val navController: NavController =
                Navigation.findNavController(this, R.id.nav_host_fragment)
            return navController
        }


    private val appBarConfiguration: AppBarConfiguration
        get() {
            val appBarConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
                R.id.square,
                R.id.encyclopedias,
                R.id.photograph,
                R.id.personal
            )
                .build()
            return appBarConfiguration
        }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setSupportActionBar(binding.mainToolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.mainBtnNav, navController)

        //跳转至编写文章页面
        binding.mainFloatingbtn.setOnClickListener {
            val intent = Intent(this,ArticleActivity::class.java)
            startActivity(intent)
        }
    }

    fun setScrollToolBar(type : Int){
        val mainToolbar = binding.mainToolbar
        val layoutParams : AppBarLayout.LayoutParams  = mainToolbar.layoutParams as AppBarLayout.LayoutParams

        /*
        *   0 标题栏可以隐藏 + 悬浮按钮可见
        *   1 标题栏不可以隐藏 + 悬浮按钮不可见 
        * */
        when(type){

            0 ->{
                layoutParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                binding.mainFloatingbtn.visibility = View.VISIBLE
            }
            1 ->{
                layoutParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
                binding.mainFloatingbtn.visibility = View.GONE
            }
            else ->{

            }
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }

}
