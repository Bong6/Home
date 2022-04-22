package com.example.home.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


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

@Entity(tableName = "encyclopediaknowledge")
class EncyclopediaKnowledge() {
    @PrimaryKey
    @ColumnInfo(name = "encyclopediaknowledge_id",typeAffinity = ColumnInfo.INTEGER)
    var encyclopediaknowledge_id : Int = 0
    @ColumnInfo(name = "imageId",typeAffinity = ColumnInfo.INTEGER)
    var imageId : Int = 0
    @ColumnInfo(name = "sickName",typeAffinity = ColumnInfo.TEXT)
    var sickName : String = ""
    @ColumnInfo(name = "symptom",typeAffinity = ColumnInfo.TEXT)
    var symptom : String = ""
    @ColumnInfo(name = "popularCharacteristics",typeAffinity = ColumnInfo.TEXT)
    var popularCharacteristics : String = ""
    @ColumnInfo(name = "preventionMethods",typeAffinity = ColumnInfo.TEXT)
    var preventionMethods : String = ""
    @ColumnInfo(name = "time",typeAffinity = ColumnInfo.TEXT)
    var time : String = ""

    constructor(
        encyclopediaknowledge_id: Int,
        imageId: Int,
        sickName: String,
        symptom: String,
        popularCharacteristics: String,
        preventionMethods: String,
        time: String
    ) : this() {
        this.encyclopediaknowledge_id = encyclopediaknowledge_id
        this.imageId = imageId
        this.sickName = sickName
        this.symptom = symptom
        this.popularCharacteristics = popularCharacteristics
        this.preventionMethods = preventionMethods
        this.time = time
    }

    override fun toString(): String {
        return "EncyclopediaKnowledge(encyclopediaknowledge_id=$encyclopediaknowledge_id, imageId=$imageId, sickName='$sickName', symptom='$symptom', popularCharacteristics='$popularCharacteristics', preventionMethods='$preventionMethods', time='$time')"
    }


}