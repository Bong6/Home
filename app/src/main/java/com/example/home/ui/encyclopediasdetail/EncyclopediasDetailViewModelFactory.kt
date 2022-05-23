package com.example.home.ui.encyclopediasdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home.repository.EncyclopediasDetailRepository
import com.example.home.repository.PersonalPublishOrCollectionRepository
import com.example.home.ui.personaldetail.PersonalPublishOrCollectionViewModel


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-22 17:56
*/

class EncyclopediasDetailViewModelFactory(val repository: EncyclopediasDetailRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EncyclopediasDetailViewModel(repository) as T
    }

}