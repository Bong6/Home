package com.example.kotlindemo.RetrofitClient


import com.example.home.utils.Flag
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/*
    @Author 10185
    @Description
        单例Retrofit实例
    @Date 2021-08-09 15:35
*/

class APIClient {

    private object Holder{
        val INSTANCE = APIClient()
    }

    companion object{
        val mInstance = Holder.INSTANCE
    }

    fun <T> create(apiClass : Class<T>) : T {

        val okHttpClient = OkHttpClient().newBuilder()
            // 添加读取超时时间
            .readTimeout(10000, TimeUnit.SECONDS)
            // 添加连接超时时间
            .connectTimeout(10000, TimeUnit.SECONDS)
            // 添加写出超时时间
            .writeTimeout(10000, TimeUnit.SECONDS)
            .build()

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(Flag.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return retrofit.create(apiClass)
    }
}