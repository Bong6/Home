package com.example.home.ui.personaldetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.LocalData
import com.example.home.repository.PersonalPublishOrCollectionRepository
import com.example.home.repository.PersonalRepository
import com.example.home.utils.Flag
import com.example.home.utils.GetInformation


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-20 10:49
*/

class PersonalPublishOrCollectionViewModel() : ViewModel() {

    private lateinit var repository: PersonalPublishOrCollectionRepository
    public lateinit var localdata : LiveData<List<LocalData>>
    public lateinit var knowledge : LiveData<List<EncyclopediaKnowledge>>


    constructor(repository: PersonalPublishOrCollectionRepository) : this(){
        this.repository = repository
        localdata = repository.queryUserPublishRecord(Flag.userId)
        knowledge = repository.queryUserCollectionRecord_EncyclopediaKnowledge(Flag.userId)
    }

}