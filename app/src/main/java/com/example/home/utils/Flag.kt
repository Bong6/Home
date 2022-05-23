package com.example.home.utils

import com.example.home.R
import com.example.home.database.entity.User

/**
 * 所有标记常用配置
 */
object Flag {

    val BASE_URL : String = "http://192.168.43.208:80/"
    val TAG = "javed"
    //本地数据版本
    var curVersion = 0
    //当前登录用户
    var user : User ?= null
    //判断是否有用户登录
    //0 ->  未登录
    var userId = 0
}