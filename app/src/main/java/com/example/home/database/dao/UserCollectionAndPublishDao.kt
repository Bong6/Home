package com.example.home.database.dao

import androidx.room.Dao
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

    //查询用户收藏的百科数据
    @Query("select encyclopediaknowledge.encyclopediaknowledge_id,encyclopediaknowledge.imageId,encyclopediaknowledge.sickName,encyclopediaknowledge.symptom,encyclopediaknowledge.popularCharacteristics,encyclopediaknowledge.preventionMethods,encyclopediaknowledge.time from encyclopediaknowledge,usercollectionandpublish where usercollectionandpublish.userId = 1 and usercollectionandpublish.collectandpublish_type = 2 and usercollectionandpublish.localorknowledge_type = 2 and encyclopediaknowledge.encyclopediaknowledge_id = usercollectionandpublish.foreignkey_id")
    fun queryUserCollectionRecord_EncyclopediaKnowledge() : List<EncyclopediaKnowledge>

    //查询用户收藏的本地数据
    @Query("select localdata.recordId,localdata.imageId,localdata.userId,localdata.userPhoto,localdata.userName,localdata.time,localdata.title,localdata.describe,localdata.answerOne,localdata.answererOne,localdata.answerOnePhoto,localdata.answerOneTime,localdata.answerTwo,localdata.answererTwo,localdata.answerTwoPhoto,localdata.answerTwoTime from localdata,usercollectionandpublish where usercollectionandpublish.userId = 1 and usercollectionandpublish.collectandpublish_type = 2 and usercollectionandpublish.localorknowledge_type = 1 and localdata.recordId = usercollectionandpublish.foreignkey_id")
    fun queryUserCollectionRecord_LocalData() : List<LocalData>
}