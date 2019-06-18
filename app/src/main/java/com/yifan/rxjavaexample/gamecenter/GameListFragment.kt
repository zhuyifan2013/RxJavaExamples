package com.yifan.rxjavaexample.gamecenter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.yifan.rxjavaexample.R
import kotlinx.android.synthetic.main.game_list.*

class GameListFragment : BaseMvRxFragment() {

    private var adapter: GameListAdapter? = null
    private val gamecenterViewModel: GameCenterViewModel by fragmentViewModel(GameCenterViewModel::class)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.game_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        game_list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = GameListAdapter(mutableListOf(), context, gamecenterViewModel)
        }
        adapter = game_list.adapter as GameListAdapter?
    }

    override fun invalidate() {
        withState(gamecenterViewModel) {
            when (it.gameList) {
                is Fail -> Log.i("Yifan", "Fail" + it.gameList.error)
                is Success -> {
                    it.gameList()?.apply {
                        adapter?.updateList(this)
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