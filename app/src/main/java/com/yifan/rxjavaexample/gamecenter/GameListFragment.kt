package com.yifan.rxjavaexample.gamecenter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import com.yifan.rxjavaexample.R

class GameListFragment : BaseMvRxFragment() {

    private val gamecenterViewModel: GameCenterViewModel by fragmentViewModel(GameCenterViewModel::class)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.game_list, null)
    }

    override fun invalidate() {
        withState(gamecenterViewModel) {
            when (it.gameList) {
                is Fail -> Log.i("Yifan", "Fail" + it.gameList.error)
                is Success -> {
                    it.gameList()?.apply {
                        for (game: Game in this) {
                            Log.i("Yifan", "gameList" + game.name)
                        }
                    }
                    Log.i("Yifan", "Success")
                }

                is Incomplete -> Log.i("Yifan", "Loading.....")
                else -> {
                    Log.i("Yifan", "else")
                }
            }

        }
    }

}