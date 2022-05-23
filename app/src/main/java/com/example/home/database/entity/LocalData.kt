package com.example.home.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


/*
    @Author 姜小龙
    @Description

                本地数据
   val recordId : Int,          数据id 主键
   val imageId : Int,           图片id
   val imageUrl : Int,          图片地址
   val userId : Int,            用户id
   val time : String,           时间
   val title : String,          标题
   val describe : String        描述

    @Date 2022-04-15 17:11
*/


@Parcelize
@Entity(tableName = "localdata")
class LocalData (
   @PrimaryKey
   @ColumnInfo(name = "recordId",typeAffinity = ColumnInfo.INTEGER)
   var recordId : Int,
   @ColumnInfo(name = "imageId",typeAffinity = ColumnInfo.TEXT)
   var imageId : String,
   @ColumnInfo(name = "userId",typeAffinity = ColumnInfo.TEXT)
   var userId : String,
   @ColumnInfo(name = "userPhoto",typeAffinity = ColumnInfo.TEXT)
   var userPhoto : String,
   @ColumnInfo(name = "userName",typeAffinity = ColumnInfo.TEXT)
   var userName : String,
   @ColumnInfo(name = "time",typeAffinity = ColumnInfo.TEXT)
   var time : String,
   @ColumnInfo(name = "title",typeAffinity = ColumnInfo.TEXT)
   var title : String,
   @ColumnInfo(name = "describe",typeAffinity = ColumnInfo.TEXT)
   var describe : String) : Parcelable {

   override fun toString(): String {
      return "LocalData(recordId=$recordId, imageId=$imageId, userId=$userId, userPhoto=$userPhoto, userName='$userName', time='$time', title='$title', describe='$describe')"
   }
}