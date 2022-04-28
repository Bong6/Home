package com.example.home.database.entity

import androidx.room.ColumnInfo


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-27 9:28
*/

data class Comment(
    var answer : String,
    var answerer : String,
    var answerPhoto : Int,
    var answerTime : String,
)