package com.example.home.ui.suqare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home.repository.LocalDataRepository


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-20 11:04
*/

class SquareViewModelFactory(val repository: LocalDataRepository) : ViewModelProvider.NewInstanceFactory()  {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SquareViewModel(repository) as T
    }


}