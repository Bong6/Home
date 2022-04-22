package com.example.home.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.home.database.entity.LocalData


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-18 23:42
*/

@Dao
interface LocalDataDao {

    //插入本地数据
    @Insert
    fun insertLocalData(list: List<LocalData>)

    //查询本地数据
    @Query("select * from localdata")
    fun queryLocalData() : LiveData<List<LocalData>>
}