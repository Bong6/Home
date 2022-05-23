package com.example.home.ui.personaldetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home.repository.PersonalPublishOrCollectionRepository


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-20 10:48
*/

class PersonalPublishOrCollectionViewModelFactory(val repository: PersonalPublishOrCollectionRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonalPublishOrCollectionViewModel(repository) as T
    }

}