package com.example.home.ui.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.home.database.entity.LocalData
import com.example.home.database.entity.User
import com.example.home.repository.LocalDataRepository
import com.example.home.repository.PersonalRepository


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-21 16:18
*/

class PersonalViewModel() : ViewModel() {

    private lateinit var repository: PersonalRepository
    public lateinit var data : LiveData<User>


    constructor(repository: PersonalRepository) : this(){
        this.repository = repository
        data = repository.getUserId_1()
    }
}