package com.yifan.rxjavaexample.gamecenter.db

import androidx.room.Dao
import androidx.room.Query
import com.yifan.rxjavaexample.gamecenter.Game
import io.reactivex.Single

@Dao
interface GameDao {

    @Query("select * from game")
    fun getGames(): Single<List<Game>>

}
