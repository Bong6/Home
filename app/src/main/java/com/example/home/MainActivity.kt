package com.example.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.request.RequestOptions.option
import com.example.home.databinding.ActivityMainBinding
import com.example.home.net.GetInformationAPI
import com.example.home.net.entity.NetLocalData
import com.example.home.utils.GetInformation
import com.example.kotlindemo.RetrofitClient.APIClient
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.mainBtnNav, navController);


    }

    fun setScrollToolBar(type : Int){
        val mainToolbar = binding.mainToolbar
        val layoutParams : AppBarLayout.LayoutParams  = mainToolbar.layoutParams as AppBarLayout.LayoutParams

        /*
        *   0 可以隐藏
        *   1 不可以隐藏
        * */
        when(type){

            0 ->{
                layoutParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
            }
            1 ->{
                layoutParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
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

    override fun onResume() {
        super.onResume()

        //模仿网络操作
        APIClient.mInstance.create(GetInformationAPI::class.java).getAction().enqueue(
            object : Callback<NetLocalData>{
                override fun onResponse(
                    call: Call<NetLocalData>,
                    response: Response<NetLocalData>
                ) {
                    if (response.body() != null){
                        val jsonData = GetInformation.getNetLocalData()
                        val gson = Gson()
                        val netLocalData = gson.fromJson(jsonData,NetLocalData::class.java)
                        Log.d("javed",netLocalData.toString())
                    }
                }

                override fun onFailure(call: Call<NetLocalData>, t: Throwable) {
                    Log.d("javed", "onFailure")
                }
            }
        )
    }
}
