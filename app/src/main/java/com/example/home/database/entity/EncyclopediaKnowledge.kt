package com.example.home.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


/*
    @Author 姜小龙
    @Description

            百科知识

    val encyclopediaknowledge_id            主键id
    val imageId : Int,                      图片id
    val sickName : String,                  疾病名称
    val symptom : String,                   病症
    val popularCharacteristics : String,    流行特点
    val preventionMethods : String,         防治方法
    val time : String                       时间

    @Date 2022-04-15 17:08
*/

@Parcelize
@Entity(tableName = "encyclopediaknowledge")
class EncyclopediaKnowledge(
    @PrimaryKey
    @ColumnInfo(name = "encyclopediaknowledge_id",typeAffinity = ColumnInfo.INTEGER)
    var encyclopediaknowledge_id : Int,
    @ColumnInfo(name = "imageId",typeAffinity = ColumnInfo.TEXT)
    var imageId : String,
    @ColumnInfo(name = "sickName",typeAffinity = ColumnInfo.TEXT)
    var sickName : String,
    @ColumnInfo(name = "symptom",typeAffinity = ColumnInfo.TEXT)
    var symptom : String,
    @ColumnInfo(name = "popularCharacteristics",typeAffinity = ColumnInfo.TEXT)
    var popularCharacteristics : String,
    @ColumnInfo(name = "preventionMethods",typeAffinity = ColumnInfo.TEXT)
    var preventionMethods : String,
    @ColumnInfo(name = "time",typeAffinity = ColumnInfo.TEXT)
    var time : String,
) : Parcelable {

    override fun toString(): String {
        return "EncyclopediaKnowledge(encyclopediaknowledge_id=$encyclopediaknowledge_id, imageId=$imageId, sickName='$sickName', symptom='$symptom', popularCharacteristics='$popularCharacteristics', preventionMethods='$preventionMethods', time='$time')"
    }
}