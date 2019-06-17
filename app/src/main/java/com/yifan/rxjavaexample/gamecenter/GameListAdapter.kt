package com.yifan.rxjavaexample.gamecenter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yifan.rxjavaexample.R
import kotlinx.android.synthetic.main.game_item.view.*


class GameListAdapter(var games: MutableList<Game>, private val context: Context) : RecyclerView.Adapter<GameItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameItemViewHolder {
        return GameItemViewHolder(LayoutInflater.from(context).inflate(R.layout.game_item, parent, false))
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: GameItemViewHolder, position: Int) {
        holder.gameName.text = games[position].name
        holder.likeGameBtn.setOnClickListener {
            Log.i("Yifan", "Click!!")
        }
    }

    fun updateList(gameList:List<Game>) {
        games.clear()
        games.addAll(gameList)
        notifyDataSetChanged()
    }

}

class GameItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val gameName = itemView.game_name
    val likeGameBtn = itemView.like_game
}