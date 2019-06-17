package com.yifan.rxjavaexample.gamecenter.db

import androidx.room.TypeConverter
import com.yifan.rxjavaexample.gamecenter.GamePlatform

class GameConverters {

    @TypeConverter
    fun platformtoString(platform: GamePlatform): String {
        return platform.getPlatform()
    }

    @TypeConverter
    fun stringToPlatform(platform: String): GamePlatform {
        return when (platform) {
            "ps4" -> GamePlatform.PS4
            "xbox" -> GamePlatform.XBOX
            "switch" -> GamePlatform.Switch
            else -> GamePlatform.PS4
        }
    }

}