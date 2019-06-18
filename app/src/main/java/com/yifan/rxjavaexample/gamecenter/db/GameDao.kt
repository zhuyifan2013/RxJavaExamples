package com.yifan.rxjavaexample.gamecenter.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yifan.rxjavaexample.gamecenter.Game
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

@Dao
interface GameDao {

    @Query("select * from game")
    fun getGames(): Single<List<Game>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun initGames(games: List<Game>):List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateGame(game: Game) : Completable
}
