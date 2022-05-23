package com.example.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.home.net.GetInformationAPI
import com.example.home.net.entity.NetLocalData
import com.example.kotlindemo.RetrofitClient.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-05 0:21
*/

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        APIClient.mInstance.create(GetInformationAPI::class.java).getLocalData().enqueue(
            object : Callback<NetLocalData> {
                override fun onResponse(
                    call: Call<NetLocalData>,
                    response: Response<NetLocalData>
                ) {
                    Log.d("javed","success")
                    Log.d("javed",response.body().toString())
                    Log.d("javed",response.body()!!.data.last().title)

                }
                override fun onFailure(call: Call<NetLocalData>, t: Throwable) {
                    Log.d("javed","onFailure")
                }
            })

//        val okHttpClient = OkHttpClient()
//        val request = Request.Builder()
//            .url("http://192.168.43.208:80/get_data.json")
//            .build()
//        try {
//            thread {
//                val response = okHttpClient.newCall(request).execute()
//                val data = response.body?.string()
//                if (data != null){
//                    val gson = Gson()
//                    val netLocalData = gson.fromJson(data,NetLocalData::class.java)
//                    Log.d("javed",netLocalData.data.last().title)
//                }
//            }
//        }catch(e : Exception){
//
//        }

    }
}


