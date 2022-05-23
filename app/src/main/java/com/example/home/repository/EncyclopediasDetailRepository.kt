package com.example.home.repository

import androidx.lifecycle.LiveData
import com.example.home.database.dao.EncyclopediaKnowledgeDao
import com.example.home.database.dao.UserCollectionAndPublishDao
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.UserCollectionAndPublish


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-22 17:57
*/

class EncyclopediasDetailRepository private constructor(private val dao : UserCollectionAndPublishDao) {

    //单例
    companion object {
        private var instance: EncyclopediasDetailRepository? = null

        fun getInstance(dao: UserCollectionAndPublishDao): EncyclopediasDetailRepository {
            instance?.let {
                return it
            }
            return EncyclopediasDetailRepository(dao).apply {
                instance = this
            }
        }
    }

    //查询某条百科知识数据是否被收藏
    fun queryUserCollectionRecord_EncyclopediaKnowledge_Whether(userId : Int,foreignkeyid : Int) : Int? {
        return dao.queryUserCollectionRecord_EncyclopediaKnowledge_Whether(userId,foreignkeyid)
    }

    //取消某条被收藏的百科知识数据
    fun cancelUserCollectionRecord(collectandpublish_id : Int){
        dao.cancelUserCollectionRecord(collectandpublish_id)
    }

    //插入某条被收藏的百科知识数据
    fun insertUserCollectionRecord(userCollectionAndPublish : UserCollectionAndPublish){
         dao.insertUserCollectionRecord(userCollectionAndPublish)
    }

}