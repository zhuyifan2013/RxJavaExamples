package com.yifan.rxjavaexample.gamecenter

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.yifan.rxjavaexample.data.Datasource
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

data class GameCenterState(
        val gameList: Async<List<Game>> = Uninitialized
) : MvRxState

class GameCenterViewModel(initialState: GameCenterState) : BaseMvRxViewModel<GameCenterState>(initialState, debugMode = true) {

    init {
        fetchGameList()
    }


    private fun fetchGameList() {

        Observable.combineLatest(mutableListOf(
                Datasource.getGameCenterService().getGameList("switch").subscribeOn(Schedulers.io()),
                Datasource.getGameCenterService().getGameList("xbox").subscribeOn(Schedulers.io()),
                Datasource.getGameCenterService().getGameList("ps4").subscribeOn(Schedulers.io()))) { it ->

            val gameList = emptyList<Game>().toMutableList()

            for (list in it) {
                gameList.addAll(list as Collection<Game>)
            }

            gameList.toList()

        }.execute { copy(gameList = it) }

    }
}

