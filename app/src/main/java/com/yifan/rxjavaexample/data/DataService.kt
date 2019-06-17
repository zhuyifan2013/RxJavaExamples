package com.yifan.rxjavaexample.data

import com.yifan.rxjavaexample.gamecenter.GameCenterService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DataService {


    companion object {
        private val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        private val gameCenterService = retrofit.create(GameCenterService::class.java)

        fun getGameCenterService():GameCenterService {
            return gameCenterService
        }
    }

}