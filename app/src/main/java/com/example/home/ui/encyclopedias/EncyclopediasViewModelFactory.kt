package com.example.home.ui.encyclopedias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home.repository.EncyclopediasRepository
import com.example.home.repository.LocalDataRepository
import com.example.home.ui.suqare.SquareViewModel


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-21 15:55
*/

class EncyclopediasViewModelFactory(val repository: EncyclopediasRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EncyclopediasViewModel(repository) as T
    }

}