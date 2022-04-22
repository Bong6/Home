package com.example.home.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.home.database.dao.EncyclopediaKnowledgeDao
import com.example.home.database.dao.LocalDataDao
import com.example.home.database.dao.UserCollectionAndPublishDao
import com.example.home.database.dao.UserDao
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.LocalData
import com.example.home.database.entity.User
import com.example.home.database.entity.UserCollectionAndPublish


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-17 16:38
*/

@Database(version = 1,entities = [User::class,LocalData::class,EncyclopediaKnowledge::class,UserCollectionAndPublish::class],exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun encyclopediaKnowledgeDao() : EncyclopediaKnowledgeDao
    abstract fun localDataDao() : LocalDataDao
    abstract fun userCollectionAndPublishDao() : UserCollectionAndPublishDao

    companion object{
        private var instance : AppDatabase ?= null

        @Synchronized
        fun getDatabase(context: Context) : AppDatabase{
            instance?.let {
                return it
            }

            return Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java,
            "app_database").build().apply {
                instance = this
            }
        }
    }
}