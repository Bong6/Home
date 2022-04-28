package com.example.home.net.entity


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-28 10:12
*/

data class Data(
    var recordId : Int,
    var imageId : Long,
    var userId : Int ,
    var userPhoto : Long,
    var userName : String,
    var time : String,
    var describe : String,
    var answerInformation : List<AnswerInformation>
) {




}