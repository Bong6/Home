package com.example.home.ui.personal


import androidx.lifecycle.ViewModel
import com.example.home.database.entity.User
import com.example.home.repository.PersonalRepository


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-21 16:18
*/

class PersonalViewModel() : ViewModel() {

    private lateinit var repository: PersonalRepository



    constructor(repository: PersonalRepository) : this(){
        this.repository = repository
    }

    //注册
    fun registerUser(userAccount : String,userPassword : String,userPasswordAgain : String){
        //1校验

        //2注册
        val user = User(
            10,
            userAccount,
            "R.mipmap.icon_default_userphoto",
            userAccount,
            userPassword,
            "2022-05-06",
            0,
            0,
            0
        )
        return repository.registerUser(user)
    }

    //登录
    fun loginUser(userAccount : String,userPassword : String) : User?{
        return repository.loginUser(userAccount,userPassword)
    }
}