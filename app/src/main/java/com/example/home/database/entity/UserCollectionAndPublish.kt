package com.example.home.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/*
    @Author 姜小龙
    @Description

        用户发表或者收藏的记录

        收藏/发表
        1 -> 发表
        2 -> 收藏

        可收藏 本地数据/百科知识
        1 ->本地数据
        2 ->百科知识



    val collectandpublish_id            主键id
    val collectandpublish_type : Int,   类型（区分发表/收藏）
    var localorknowledge_type : Int = 0 类型（区分本地/百科）
    val foreignkey_id                   外键id
    val userId : Int                    用户id



    @Date 2022-04-15 10:57
*/

@Entity(tableName = "usercollectionandpublish")
class UserCollectionAndPublish(){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "collectandpublish_id",typeAffinity = ColumnInfo.INTEGER)
    var collectandpublish_id : Int = 0
    @ColumnInfo(name = "collectandpublish_type",typeAffinity = ColumnInfo.INTEGER)
    var collectandpublish_type : Int = 0
    @ColumnInfo(name = "localorknowledge_type",typeAffinity = ColumnInfo.INTEGER)
    var localorknowledge_type : Int = 0
    @ColumnInfo(name = "foreignkey_id",typeAffinity = ColumnInfo.INTEGER)
    var foreignkey_id : Int = 0
    @ColumnInfo(name = "userId",typeAffinity = ColumnInfo.INTEGER)
    var userId : Int = 0


    constructor(
        collectandpublish_type: Int,
        localorknowledge_type : Int,
        foreignkey_id: Int,
        userId: Int,
    ) : this() {
        this.collectandpublish_type = collectandpublish_type
        this.localorknowledge_type = localorknowledge_type
        this.foreignkey_id = foreignkey_id
        this.userId = userId
    }

    constructor(
        collectandpublish_id : Int,
        collectandpublish_type: Int,
        localorknowledge_type : Int,
        foreignkey_id: Int,
        userId: Int,
    ) : this() {
        this.collectandpublish_id = collectandpublish_id
        this.collectandpublish_type = collectandpublish_type
        this.localorknowledge_type = localorknowledge_type
        this.foreignkey_id = foreignkey_id
        this.userId = userId
    }

    override fun toString(): String {
        return "UserCollectionAndPublish(collectandpublish_id=$collectandpublish_id, collectandpublish_type=$collectandpublish_type, localorknowledge_type=$localorknowledge_type, foreignkey_id=$foreignkey_id, userId=$userId)"
    }


}