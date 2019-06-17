package com.yifan.rxjavaexample.gamecenter

import android.util.Log
import com.airbnb.mvrx.*
import com.yifan.rxjavaexample.data.DataService
import com.yifan.rxjavaexample.gamecenter.db.GameDao
import com.yifan.rxjavaexample.gamecenter.db.GameDatabase
import com.yifan.rxjavaexample.gamecenter.db.GameDatasource
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

data class GameCenterState(
        val gameList: Async<List<Game>> = Uninitialized
) : MvRxState

class GameCenterViewModel(initialState: GameCenterState, val datasource: GameDatasource) : BaseMvRxViewModel<GameCenterState>(initialState, debugMode = true) {

    init {
        fetchGameList()
    }


    private fun fetchGameList() {

//        Observable.combineLatest(mutableListOf(
//                getGameListObservable(GamePlatform.PS4),
//                getGameListObservable(GamePlatform.XBOX),
//                getGameListObservable(GamePlatform.Switch))) { it ->
//
//            val gameList = emptyList<Game>().toMutableList()
//
//            for (list in it) {
//                gameList.addAll(list as Collection<Game>)
//            }
//
//            gameList.toList()
//
//        }.execute { copy(gameList = it) }

        datasource.getGames().toObservable()
                .doOnSubscribe {
                    Log.i("Yifan", "loading...");
                }
                .doOnComplete {
                    Log.i("Yifan", "load finish");
                }
                .execute { copy(gameList = it) }

    }

//    private fun getGameListObservable(platform: GamePlatform): Observable<List<Game>> {
//        DataService.getGameCenterService()
//                .getGameList(platform.getPlatform())
//                .subscribeOn(Schedulers.io())
//                .map { gameList: List<Game> ->
//                    {
//                        val newGameList = emptyList<Game>().toMutableList()
//                        for (game in gameList) {
//                            game.platform = platform
//                            newGameList.add(game)
//                        }
//                        newGameList
//                    }
//                }
//
//    }

    public fun likeGame() {

    }

    companion object : MvRxViewModelFactory<GameCenterViewModel, GameCenterState> {

        override fun create(viewModelContext: ViewModelContext, state: GameCenterState): GameCenterViewModel? {
            val database = GameDatabase.getInstance(viewModelContext.activity)

            return GameCenterViewModel(state, GameDatasource(database.gameDao()))
        }

    }

}

