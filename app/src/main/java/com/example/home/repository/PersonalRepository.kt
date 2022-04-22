package com.example.home.repository

import androidx.lifecycle.LiveData
import com.example.home.database.dao.LocalDataDao
import com.example.home.database.dao.UserDao
import com.example.home.database.entity.LocalData
import com.example.home.database.entity.User


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-21 16:16
*/

class PersonalRepository private constructor(private val dao : UserDao) {

    //单例
    companion object{
        private var instance : PersonalRepository ?= null

        fun getInstance(dao : UserDao) : PersonalRepository{
            instance?.let {
                return it
            }
            return PersonalRepository(dao).apply {
                instance = this
            }
        }
    }

    //获取userId = 1 的用户信息
    fun getUserId_1(): LiveData<User> {
        return dao.queryUserId_1()
    }

}