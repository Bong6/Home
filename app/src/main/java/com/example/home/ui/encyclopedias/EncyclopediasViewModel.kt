package com.example.home.ui.encyclopedias

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.LocalData
import com.example.home.repository.EncyclopediasRepository
import com.example.home.repository.LocalDataRepository


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-21 15:56
*/

class EncyclopediasViewModel() : ViewModel() {

    private lateinit var repository: EncyclopediasRepository
    public lateinit var data : LiveData<List<EncyclopediaKnowledge>>


    constructor(repository: EncyclopediasRepository) : this(){
        this.repository = repository
        data = repository.getEncyclopediaKnowledge()
    }

}