package com.yifan.rxjavaexample.gamecenter.db

import com.yifan.rxjavaexample.gamecenter.Game
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class GameDatasource(
        private val dao: GameDao
) : GameDao {
    override fun getGames(): Single<List<Game>> = dao.getGames()
            .subscribeOn(Schedulers.io())
            .delay(1500, TimeUnit.MILLISECONDS)
}