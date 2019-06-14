package com.yifan.rxjavaexample.gamecenter

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GameCenterService {

    @GET("game/{platform}")
    fun getGameList(@Path("platform") platform:String) : Observable<List<Game>>

}