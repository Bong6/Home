package com.example.home.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.LocalData
import com.example.home.database.entity.UserCollectionAndPublish


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-18 23:57
*/

@Dao
interface UserCollectionAndPublishDao {

    //插入数据
    @Insert
    fun insertUserCollectionAndPublish(list: List<UserCollectionAndPublish>)

    //查询所有数据
    @Query("select * from usercollectionandpublish")
    fun queryUsercollectionAndPublish() : List<UserCollectionAndPublish>

    //查询某条百科知识数据是否被收藏
    @Query("select usercollectionandpublish.collectandpublish_id from usercollectionandpublish where usercollectionandpublish.userId = :userId and usercollectionandpublish.collectandpublish_type = 2 and usercollectionandpublish.localorknowledge_type = 2 and usercollectionandpublish.foreignkey_id = :foreignkeyid")
    fun queryUserCollectionRecord_EncyclopediaKnowledge_Whether(userId : Int,foreignkeyid : Int) : Int?

    //取消某条被收藏的百科知识数据
    @Query("delete from usercollectionandpublish where usercollectionandpublish.collectandpublish_id =:collectandpublish_id")
    fun cancelUserCollectionRecord(collectandpublish_id : Int)

    //插入某条被收藏的百科知识数据
    @Insert
    fun insertUserCollectionRecord(userCollectionAndPublish : UserCollectionAndPublish)

    //查询用户收藏的百科数据
    @Query("select encyclopediaknowledge.encyclopediaknowledge_id,encyclopediaknowledge.imageId,encyclopediaknowledge.sickName,encyclopediaknowledge.symptom,encyclopediaknowledge.popularCharacteristics,encyclopediaknowledge.preventionMethods,encyclopediaknowledge.time from encyclopediaknowledge,usercollectionandpublish where usercollectionandpublish.userId = :userId and usercollectionandpublish.collectandpublish_type = 2 and usercollectionandpublish.localorknowledge_type = 2 and encyclopediaknowledge.encyclopediaknowledge_id = usercollectionandpublish.foreignkey_id")
    fun queryUserCollectionRecord_EncyclopediaKnowledge(userId : Int) : LiveData<List<EncyclopediaKnowledge>>

    //查询用户收藏的本地数据
    @Query("select localdata.recordId,localdata.imageId,localdata.userId,localdata.userPhoto,localdata.userName,localdata.time,localdata.title,localdata.describe from localdata,usercollectionandpublish where usercollectionandpublish.userId = :userId and usercollectionandpublish.collectandpublish_type = 2 and usercollectionandpublish.localorknowledge_type = 1 and localdata.recordId = usercollectionandpublish.foreignkey_id")
    fun queryUserCollectionRecord_LocalData(userId : Int) : LiveData<List<LocalData>>

    //查询用户曾发表过的本地数据
    @Query("select localdata.recordId,localdata.imageId,localdata.userId,localdata.userPhoto,localdata.userName,localdata.time,localdata.title,localdata.describe from localdata,usercollectionandpublish where usercollectionandpublish.userId = :userId and usercollectionandpublish.collectandpublish_type = 1 and usercollectionandpublish.localorknowledge_type = 1 and localdata.recordId = usercollectionandpublish.foreignkey_id")
    fun queryUserPublishRecord(userId : Int) : LiveData<List<LocalData>>

}