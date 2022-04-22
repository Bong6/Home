package com.example.home.repository

import androidx.lifecycle.LiveData
import com.example.home.database.dao.EncyclopediaKnowledgeDao
import com.example.home.database.dao.LocalDataDao
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.LocalData


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-21 15:57
*/

class EncyclopediasRepository private constructor(private val dao : EncyclopediaKnowledgeDao) {


    //单例
    companion object{
        private var instance : EncyclopediasRepository ?= null

        fun getInstance(dao : EncyclopediaKnowledgeDao) : EncyclopediasRepository{
            instance?.let {
                return it
            }
            return EncyclopediasRepository(dao).apply {
                instance = this
            }
        }
    }

    //获取本地数据
    fun getEncyclopediaKnowledge(): LiveData<List<EncyclopediaKnowledge>> {
        return dao.queryEncyclopediaKnowledge()
    }

}