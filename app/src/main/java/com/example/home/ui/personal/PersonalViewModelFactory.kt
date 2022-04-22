package com.example.home.ui.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home.repository.EncyclopediasRepository
import com.example.home.repository.PersonalRepository
import com.example.home.ui.encyclopedias.EncyclopediasViewModel


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-21 16:19
*/

class PersonalViewModelFactory(val repository: PersonalRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonalViewModel(repository) as T
    }

}