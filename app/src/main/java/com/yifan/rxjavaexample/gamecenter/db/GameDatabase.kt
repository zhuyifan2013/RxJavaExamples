package com.yifan.rxjavaexample.gamecenter.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yifan.rxjavaexample.gamecenter.Game

@Database(entities = [Game::class], version = 1)
@TypeConverters(GameConverters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao():GameDao

    companion object {
        private var INSTANCE: GameDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): GameDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.applicationContext, GameDatabase::class.java, "game.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}