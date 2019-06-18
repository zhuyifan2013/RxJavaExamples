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
//        initDb()
        fetchGameList()
    }

    private fun initDb() {

        val games = listOf(
                Game(0, 1, "PS41", GamePlatform.PS4, "2019-01-01", 0, null),
                Game(0, 2, "PS42", GamePlatform.PS4, "2019-01-04", 0, null),
                Game(0, 3, "PS43", GamePlatform.PS4, "2019-01-08", 0, null),
                Game(0, 4, "PS44", GamePlatform.PS4, "2019-01-14", 0, null),
                Game(0, 1, "XBOX1", GamePlatform.XBOX, "2019-01-02", 0, null),
                Game(0, 2, "XBOX2", GamePlatform.XBOX, "2019-01-06", 0, null),
                Game(0, 3, "XBOX3", GamePlatform.XBOX, "2019-01-09", 0, null),
                Game(0, 1, "SWITCH1", GamePlatform.Switch, "2019-01-03", 0, null),
                Game(0, 2, "SWITCH2", GamePlatform.Switch, "2019-01-10", 0, null),
                Game(0, 3, "SWITCH3", GamePlatform.Switch, "2019-01-18", 0, null)
        )
        datasource.initGames(games)
    }

    private fun fetchGameList() {
        Log.i("Yifan", "fetchGameList")

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
                    Log.i("Yifan", "loading...")
                }
                .doOnComplete {
                    Log.i("Yifan", "load finish")
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

    fun likeGame(game: Game) {
        val newGame = game.copy(likeCount = game.likeCount + 1)
        datasource.updategame(newGame).subscribe({
            fetchGameList()
        }, { e ->
            Log.i("Yifan", "Something wrong " + e)
        })
    }

    companion object : MvRxViewModelFactory<GameCenterViewModel, GameCenterState> {

        override fun create(viewModelContext: ViewModelContext, state: GameCenterState): GameCenterViewModel? {
            val database = GameDatabase.getInstance(viewModelContext.activity)

            return GameCenterViewModel(state, GameDatasource(database.gameDao()))
        }

    }

}

