package com.example.home.ui.encyclopediasdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.UserCollectionAndPublish
import com.example.home.repository.EncyclopediasDetailRepository
import com.example.home.repository.PersonalPublishOrCollectionRepository
import com.example.home.utils.Flag
import com.example.home.utils.GetInformation


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-22 17:55
*/

class EncyclopediasDetailViewModel() : ViewModel(){

    private lateinit var repository: EncyclopediasDetailRepository
    public var hasCollected = MutableLiveData<Boolean>(false)
    public var collectandpublish_id = MutableLiveData<Int>(null)

    constructor(repository: EncyclopediasDetailRepository) : this(){
        this.repository = repository
    }

    //查询某条百科知识数据是否被收藏
    fun queryUserCollectionRecord_EncyclopediaKnowledge_Whether(userId : Int,foreignkeyid : Int){

        val id = repository.queryUserCollectionRecord_EncyclopediaKnowledge_Whether(
                userId,
                foreignkeyid
            )

        collectandpublish_id.postValue(id)
        hasCollected.postValue( id != null)
    }

    //取消某条被收藏的百科知识数据
    fun cancelUserCollectionRecord(id : Int){
        repository.cancelUserCollectionRecord(id)
        hasCollected.postValue(false)
        collectandpublish_id.postValue(null)
    }

    //插入某条被收藏的百科知识数据
    fun insertUserCollectionRecord(userCollectionAndPublish : UserCollectionAndPublish){
        repository.insertUserCollectionRecord(userCollectionAndPublish)
        //更新数据
        queryUserCollectionRecord_EncyclopediaKnowledge_Whether(userCollectionAndPublish.userId,userCollectionAndPublish.foreignkey_id)
    }
}