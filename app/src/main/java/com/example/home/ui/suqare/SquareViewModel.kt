package com.example.home.ui.suqare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.home.database.entity.LocalData
import com.example.home.repository.LocalDataRepository


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-20 11:07
*/

class SquareViewModel() : ViewModel() {

    private lateinit var repository: LocalDataRepository

    public lateinit var data : LiveData<List<LocalData>>

    constructor(repository: LocalDataRepository) : this(){
        this.repository = repository
        data = repository.getLocalData()
    }

    public fun getLocalDataRepository() : LocalDataRepository{
        return repository
    }

}