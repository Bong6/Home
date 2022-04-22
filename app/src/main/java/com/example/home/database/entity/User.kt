package com.example.home.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/*
    @Author 姜小龙
    @Description


        用户信息
    var userId : Int,               用户id
    var userName : String,          用户名
    var userPhoto : Int,            用户头像
    val userAccount : Int,          账号
    var userPassword : String,      密码
    var registerTime : String,      注册时间
    var publishAccount : Int,       发表数量
    var collectionAccount : Int,    收藏数量
    var scanningAccount : Int       识别数量

    @Date 2022-03-31 14:46
*/
@Entity(tableName = "user")
class User(
){
    @PrimaryKey
    @ColumnInfo(name = "userId",typeAffinity = ColumnInfo.INTEGER)
    var userId : Int = 0
    @ColumnInfo(name = "userName",typeAffinity = ColumnInfo.TEXT)
    var userName : String = ""
    @ColumnInfo(name = "userPhoto",typeAffinity = ColumnInfo.INTEGER)
    var userPhoto : Int = 0
    @ColumnInfo(name = "userAccount",typeAffinity = ColumnInfo.TEXT)
    var userAccount : String = ""
    @ColumnInfo(name = "userPassword",typeAffinity = ColumnInfo.TEXT)
    var userPassword : String = ""
    @ColumnInfo(name = "registerTime",typeAffinity = ColumnInfo.TEXT)
    var registerTime : String = ""
    @ColumnInfo(name = "publishAccount",typeAffinity = ColumnInfo.INTEGER)
    var publishAccount : Int = 0
    @ColumnInfo(name = "collectionAccount",typeAffinity = ColumnInfo.INTEGER)
    var collectionAccount : Int = 0
    @ColumnInfo(name = "scanningAccount",typeAffinity = ColumnInfo.INTEGER)
    var scanningAccount : Int = 0

    constructor(
        userId: Int,
        userName: String,
        userPhoto: Int,
        userAccount: String,
        userPassword: String,
        registerTime: String,
        publishAccount: Int,
        collectionAccount: Int,
        scanningAccount: Int
    ) : this() {
        this.userId = userId
        this.userName = userName
        this.userPhoto = userPhoto
        this.userAccount = userAccount
        this.userPassword = userPassword
        this.registerTime = registerTime
        this.publishAccount = publishAccount
        this.collectionAccount = collectionAccount
        this.scanningAccount = scanningAccount
    }

    override fun toString(): String {
        return "User(userId=$userId, userName='$userName', userPhoto=$userPhoto, userAccount='$userAccount', userPassword='$userPassword', registerTime='$registerTime', publishAccount=$publishAccount, collectionAccount=$collectionAccount, scanningAccount=$scanningAccount)"
    }


}