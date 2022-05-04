package com.example.home.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.home.database.dao.LocalDataDao
import com.example.home.database.entity.LocalData


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-25 23:35
*/

class LocalDataRepository private constructor(private val dao : LocalDataDao) {

    //单例
    companion object{
        private var instance : LocalDataRepository ?= null

        fun getInstance(dao : LocalDataDao) : LocalDataRepository{
            instance?.let {
                return it
            }
            return LocalDataRepository(dao).apply {
                instance = this
            }
        }
    }

    //插入数据
    fun insertLocalData(list: List<LocalData>){
        dao.insertLocalData(list)
    }

    //获取本地数据
    fun getLocalData(): LiveData<List<LocalData>> {
        return dao.queryLocalData()
    }


}