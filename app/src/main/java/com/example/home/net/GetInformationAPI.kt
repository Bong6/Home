package com.example.home.net

import com.example.home.net.entity.NetLocalData
import retrofit2.Call
import retrofit2.http.GET


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-27 17:57
*/

interface GetInformationAPI {

    @GET("/top/list?idx=0")
    fun getAction() : Call<NetLocalData>

}