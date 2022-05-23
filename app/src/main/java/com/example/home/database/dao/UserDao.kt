package com.example.home.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.home.database.entity.User


/*
    @Author 姜小龙
    @Description

            用户信息接口

    @Date 2022-04-17 16:41
*/
@Dao
interface UserDao {

    //插入一组数据
    @Insert
    fun insertUser(list: List<User>)

    //插入单个数据
    @Insert
    fun registerUser(user: User)

    //更新数据
    @Update
    fun updataUser(user: User)

    //查询所有用户信息
    @Query("select * from user")
    fun queryUser() :List<User>

    //登录
    @Query("select * from user where userAccount =:userAccount and userPassword =:userPassword")
    fun loginUser(userAccount : String,userPassword : String) : User?
}