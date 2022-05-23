package com.example.home.utils

import android.content.Context
import com.example.home.database.AppDatabase
import com.example.home.repository.*
import com.example.home.ui.encyclopedias.EncyclopediasViewModelFactory
import com.example.home.ui.encyclopediasdetail.EncyclopediasDetailViewModelFactory
import com.example.home.ui.personaldetail.PersonalPublishOrCollectionViewModelFactory
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

        //获取用户发表/收藏的Personal仓库
        fun getPersonalPublishOrCollectionRepository(context: Context) : PersonalPublishOrCollectionRepository {
            val userCollectionAndPublishDao = AppDatabase.getDatabase(context).userCollectionAndPublishDao()
            return PersonalPublishOrCollectionRepository.getInstance(userCollectionAndPublishDao)
        }

        //获取用户信息Personal的ViewModel工厂
        fun providePersonalPublishOrCollectionViewModelFactory(context: Context) : PersonalPublishOrCollectionViewModelFactory {
            val repository = getPersonalPublishOrCollectionRepository(context)
            return PersonalPublishOrCollectionViewModelFactory(repository)
        }

        //获取用户发表/收藏的EncyclopediasDetail仓库
        fun getEncyclopediasDetailRepository(context: Context) : EncyclopediasDetailRepository {
            val userCollectionAndPublishDao = AppDatabase.getDatabase(context).userCollectionAndPublishDao()
            return EncyclopediasDetailRepository.getInstance(userCollectionAndPublishDao)
        }

        //获取用户发表/收藏的EncyclopediasDetail的ViewModel工厂
        fun provideEncyclopediasDetailViewModelFactory(context: Context) : EncyclopediasDetailViewModelFactory {
            val repository = getEncyclopediasDetailRepository(context)
            return EncyclopediasDetailViewModelFactory(repository)
        }

    }
}