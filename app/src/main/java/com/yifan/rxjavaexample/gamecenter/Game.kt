package com.yifan.rxjavaexample.gamecenter

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yifan.rxjavaexample.gamecenter.db.GameConverters

@Entity(tableName = "game")
data class Game(

        @PrimaryKey
        @ColumnInfo(name="id")
        val id:Int,

        @ColumnInfo(name="platform_id")
        val platformId: Int,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "platform")
        var platform: GamePlatform,

        @ColumnInfo(name = "release_date")
        val releaseDate: String,

        @ColumnInfo(name = "like_count")
        var likeCount: Int,

        @ColumnInfo(name = "image_url")
        val imageUrl: String
)

enum class GamePlatform {
    PS4, Switch, XBOX;

    fun getPlatform():String {
        return when(this) {
            PS4 -> "ps4"
            Switch -> "switch"
            XBOX -> "xbox"
        }
    }
}


