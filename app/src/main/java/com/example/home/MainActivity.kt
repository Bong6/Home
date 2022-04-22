package com.example.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.home.databinding.ActivityMainBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //设置顶部标题栏
        setSupportActionBar(binding.mainToolbar)
//        if (supportActionBar != null) {
//            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_2)
//        }

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.mainBtnNav, navController);

    }



    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }

}