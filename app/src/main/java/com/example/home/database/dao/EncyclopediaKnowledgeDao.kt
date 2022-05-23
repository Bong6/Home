package com.example.home.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.home.database.entity.EncyclopediaKnowledge


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-18 21:15
*/

@Dao
interface EncyclopediaKnowledgeDao {

    //插入数据
    @Insert
    fun insertEncyclopediaKnowledgeDao(list: List<EncyclopediaKnowledge>)

    //查询所有数据
    @Query("select * from encyclopediaknowledge")
    fun queryEncyclopediaKnowledge() : LiveData<List<EncyclopediaKnowledge>>

    //查询某一疾病信息
    @Query("select * from encyclopediaknowledge where sickName = :sickName")
    fun queryExactlyEncyclopediaknowledge(sickName : String) : EncyclopediaKnowledge?
}