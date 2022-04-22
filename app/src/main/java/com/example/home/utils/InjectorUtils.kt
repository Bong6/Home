package com.example.home.utils

import android.content.Context
import com.example.home.database.AppDatabase
import com.example.home.repository.EncyclopediasRepository
import com.example.home.repository.LocalDataRepository
import com.example.home.repository.PersonalRepository
import com.example.home.ui.encyclopedias.EncyclopediasViewModelFactory
import com.example.home.ui.personal.PersonalViewModelFactory
import com.example.home.ui.suqare.SquareViewModelFactory


/*
    @Author 姜小龙
    @Description


            数据注入层


    @Date 2022-04-20 12:43
*/

class InjectorUtils {

    companion object{

        //获取本地数据LocalData仓库
        fun getLocalDataRepository(context: Context) : LocalDataRepository{
            val localDataDao = AppDatabase.getDatabase(context).localDataDao()
            return LocalDataRepository.getInstance(localDataDao)
        }

        //获取本地数据LocalData的ViewModel工厂
        fun provideLocalDataListViewModelFactory(context: Context) : SquareViewModelFactory{
            val repository = getLocalDataRepository(context)
            return SquareViewModelFactory(repository)
        }

        //获取百科知识EncyclopediaKnowledge仓库
        fun getEncyclopediaRepository(context: Context) : EncyclopediasRepository{
            val encyclopediaDao = AppDatabase.getDatabase(context).encyclopediaKnowledgeDao()
            return EncyclopediasRepository.getInstance(encyclopediaDao)
        }

        //获取百科知识EncyclopediaKnowledge的ViewModel工厂
        fun provideEncyclopediaViewModelFactory(context: Context) : EncyclopediasViewModelFactory{
            val repository = getEncyclopediaRepository(context)
            return EncyclopediasViewModelFactory(repository)
        }

        //获取用户信息Personal仓库
        fun getPersonalRepository(context: Context) : PersonalRepository{
            val userDao = AppDatabase.getDatabase(context).userDao()
            return PersonalRepository.getInstance(userDao)
        }

        //获取用户信息Personal的ViewModel工厂
        fun providePersonalViewModelFactory(context: Context) : PersonalViewModelFactory {
            val repository = getPersonalRepository(context)
            return PersonalViewModelFactory(repository)
        }
    }
}