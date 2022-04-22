package com.example.home.database.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


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


@Entity(tableName = "localdata")
class LocalData () : Parcelable{

   @PrimaryKey
   @ColumnInfo(name = "recordId",typeAffinity = ColumnInfo.INTEGER)
   var recordId : Int = 0
   @ColumnInfo(name = "imageId",typeAffinity = ColumnInfo.INTEGER)
   var imageId : Int = 0
   @ColumnInfo(name = "userId",typeAffinity = ColumnInfo.INTEGER)
   var userId : Int = 0
   @ColumnInfo(name = "userPhoto",typeAffinity = ColumnInfo.INTEGER)
   var userPhoto : Int = 0
   @ColumnInfo(name = "userName",typeAffinity = ColumnInfo.TEXT)
   var userName : String = ""
   @ColumnInfo(name = "time",typeAffinity = ColumnInfo.TEXT)
   var time : String = ""
   @ColumnInfo(name = "title",typeAffinity = ColumnInfo.TEXT)
   var title : String = ""
   @ColumnInfo(name = "describe",typeAffinity = ColumnInfo.TEXT)
   var describe : String = ""

   @ColumnInfo(name = "answerOne",typeAffinity = ColumnInfo.TEXT)
   var answerOne : String = ""
   @ColumnInfo(name = "answererOne",typeAffinity = ColumnInfo.TEXT)
   var answererOne : String = ""
   @ColumnInfo(name = "answerOnePhoto",typeAffinity = ColumnInfo.INTEGER)
   var answerOnePhoto : Int = 0
   @ColumnInfo(name = "answerOneTime",typeAffinity = ColumnInfo.TEXT)
   var answerOneTime : String = ""
   @ColumnInfo(name = "answerTwo",typeAffinity = ColumnInfo.TEXT)
   var answerTwo : String = ""
   @ColumnInfo(name = "answererTwo",typeAffinity = ColumnInfo.TEXT)
   var answererTwo : String = ""
   @ColumnInfo(name = "answerTwoPhoto",typeAffinity = ColumnInfo.INTEGER)
   var answerTwoPhoto : Int = 0
   @ColumnInfo(name = "answerTwoTime",typeAffinity = ColumnInfo.TEXT)
   var answerTwoTime : String = ""

   constructor(
      recordId: Int,
      imageId: Int,
      userId: Int,
      userPhoto : Int,
      userName : String,
      time: String,
      title: String,
      describe: String,
      answerOne : String,
      answererOne : String,
      answerOnePhoto : Int,
      answerOneTime : String,
      answerTwo : String,
      answererTwo : String,
      answerTwoPhoto : Int,
      answerTwoTime : String
   ) : this() {
      this.recordId = recordId
      this.imageId = imageId
      this.userId = userId
      this.userPhoto = userPhoto
      this.userName = userName
      this.time = time
      this.title = title
      this.describe = describe
      this.answerOne = answerOne
      this.answererOne = answererOne
      this.answerOnePhoto = answerOnePhoto
      this.answerOneTime = answerOneTime
      this.answerTwo = answerTwo
      this.answererTwo = answererTwo
      this.answerTwoPhoto = answerTwoPhoto
      this.answerTwoTime = answerTwoTime
   }

   override fun toString(): String {
      return "LocalData(recordId=$recordId, imageId=$imageId, userId=$userId, userPhoto=$userPhoto, userName='$userName', time='$time', title='$title', describe='$describe', answerOne='$answerOne', answererOne='$answererOne', answerOnePhoto=$answerOnePhoto, answerOneTime='$answerOneTime', answerTwo='$answerTwo', answererTwo='$answererTwo', answerTwoPhoto=$answerTwoPhoto, answerTwoTime='$answerTwoTime')"
   }


   //序列化
   private var `in`: Parcel
      get() {
         return `in`
      }
   set(value) {
      `in` = value
   }

   constructor(`in`: Parcel) : this(){
      this.`in` = `in`
   }


   override fun describeContents(): Int {
      return 0
   }

   override fun writeToParcel(out: Parcel?, flags: Int) {
      out?.writeInt(recordId)
      out?.writeInt(imageId)
      out?.writeInt(userId)
      out?.writeInt(userPhoto)
      out?.writeString(userName)
      out?.writeString(time)
      out?.writeString(title)
      out?.writeString(describe)
      out?.writeString(answerOne)
      out?.writeString(answererOne)
      out?.writeInt(answerOnePhoto)
      out?.writeString(answerOneTime)
      out?.writeString(answerTwo)
      out?.writeString(answererTwo)
      out?.writeInt(answerTwoPhoto)
      out?.writeString(answerTwoTime)
   }


   companion object CREATOR: Parcelable.Creator<LocalData?> {

      override fun newArray(size: Int): Array<LocalData?> {
         return arrayOfNulls(size)
      }

      override fun createFromParcel(p0: Parcel): LocalData {
         return LocalData(p0)
      }
   }

}