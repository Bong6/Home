package com.example.home.repository

import androidx.lifecycle.LiveData
import com.example.home.database.dao.UserCollectionAndPublishDao
import com.example.home.database.dao.UserDao
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.LocalData


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-20 10:46
*/

class PersonalPublishOrCollectionRepository private constructor(private val dao : UserCollectionAndPublishDao) {

    //单例
    companion object{
        private var instance : PersonalPublishOrCollectionRepository ?= null

        fun getInstance(dao : UserCollectionAndPublishDao) : PersonalPublishOrCollectionRepository{
            instance?.let {
                return it
            }
            return PersonalPublishOrCollectionRepository(dao).apply {
                instance = this
            }
        }
    }

    //查询用户曾发表过的本地数据
    fun queryUserPublishRecord(userId : Int) : LiveData<List<LocalData>> {
        return dao.queryUserPublishRecord(userId)
    }

    //查询用户曾收藏过的百科知识数据
    fun queryUserCollectionRecord_EncyclopediaKnowledge(userId : Int) : LiveData<List<EncyclopediaKnowledge>>{
        return dao.queryUserCollectionRecord_EncyclopediaKnowledge(userId)
    }
}