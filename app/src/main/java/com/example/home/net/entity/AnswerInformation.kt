package com.example.home.net.entity
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-28 10:19
*/

@Parcelize
data class AnswerInformation(
    var answerName : String,
    var answerPhoto : String,
    var answerDescribe : String,
    var answerTime : String
)  : Parcelable {

}