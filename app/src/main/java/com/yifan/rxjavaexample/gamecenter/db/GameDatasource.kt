package com.yifan.rxjavaexample.gamecenter.db

import com.yifan.rxjavaexample.gamecenter.Game
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class GameDatasource(
        private val dao: GameDao
) {

    fun getGames(): Single<List<Game>> = dao.getGames()
            .subscribeOn(Schedulers.io())
            .delay(1500, TimeUnit.MILLISECONDS)

    fun initGames(games: List<Game>): Disposable {
        return fromAction { dao.initGames(games) }
    }

    fun updategame(game: Game): Completable {
        return dao.updateGame(game).subscribeOn(Schedulers.io())
    }

    private fun fromAction(action: () -> Unit): Disposable = Completable.fromAction(action)
            .subscribeOn(Schedulers.io())
            .delay(1500, TimeUnit.MILLISECONDS)
            .subscribe()

}