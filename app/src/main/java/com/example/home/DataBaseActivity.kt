package com.example.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.home.database.AppDatabase
import com.example.home.databinding.ActivityDatabaseBinding
import com.example.home.database.entity.User
import com.example.home.utils.GetInformation
import kotlin.concurrent.thread


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-17 16:34
*/

class DataBaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityDatabaseBinding = DataBindingUtil.setContentView(this,R.layout.activity_database)


        val userDao = AppDatabase.getDatabase(this).userDao()

        val list = GetInformation.getUserInformation()

        binding.buttonInsertUser.setOnClickListener {
            thread {
                userDao.insertUser(list)
            }
        }

        binding.buttonQueryUser.setOnClickListener {
            thread {
                val list = userDao.queryUser()
                for (user in list){
                    Log.d("javed",user.toString())
                }
            }
        }

        binding.buttonUpdateUser.setOnClickListener {
            thread {
                val user1 = User(
                    1,
                    "龙灯",
                    R.mipmap.image_userphoto_1,
                    "admin",
                    "123456",
                    "2022-04-01",
                    1,
                    0,
                    0
                )

                userDao.updataUser(user1)
            }
        }


        val encyclopediaKnowledgeDao = AppDatabase.getDatabase(this).encyclopediaKnowledgeDao()
        val list2 = GetInformation.getEncyclopediaKnowledge()

        binding.buttonKnowledgeInsert.setOnClickListener {
            thread {
                encyclopediaKnowledgeDao.insertEncyclopediaKnowledgeDao(list2)
            }
        }

        binding.buttonKnowledgeQuery.setOnClickListener {
            thread {
                val list = encyclopediaKnowledgeDao.queryEncyclopediaKnowledge()
                Log.d("javed",list.value.toString())
            }
        }

        val localDataDao = AppDatabase.getDatabase(this).localDataDao()
        //取消从本地获取信息，改为从网络中获取
//        val list3 = GetInformation.getLocalData()
//
//        binding.buttonLocaldataInsert.setOnClickListener {
//            thread {
//                localDataDao.insertLocalData(list3)
//            }
//        }

        binding.buttonLocaldataQuery.setOnClickListener {
            thread {
                val list = localDataDao.queryLocalData()

                Log.d("javed",list.toString())
                Log.d("javed",list.value.toString())

            }
        }

        val userCollectionAndPublishDao = AppDatabase.getDatabase(this).userCollectionAndPublishDao()
        binding.buttonInsertUsercollectionandpublish.setOnClickListener {
            thread {
                val list = GetInformation.getUserCollectionAndPublish()
                userCollectionAndPublishDao.insertUserCollectionAndPublish(list)
            }
        }

        binding.buttonQueryUsercollectionandpublish.setOnClickListener {
            thread {
                val list = userCollectionAndPublishDao.queryUsercollectionAndPublish()
                for (a in list){
                    Log.d("javed",a.toString())
                }
            }
        }

        binding.buttonQueryUsercollectionandpublishLocaldata.setOnClickListener {
            thread {
                val list = userCollectionAndPublishDao.queryUserCollectionRecord_LocalData()
                for (a in list){
                    Log.d("javed",a.toString())
                }
            }
        }

        binding.buttonQueryUsercollectionandpublishKnowledge.setOnClickListener {
            thread {
                val list = userCollectionAndPublishDao.queryUserCollectionRecord_EncyclopediaKnowledge()
                for (a in list){
                    Log.d("javed",a.toString())
                }
            }
        }
    }
}