package com.example.beersapp.room.model.room

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken




class RoomBeerTypeConverter {

    @TypeConverter
    fun restoreList(listOfString: String): List<String>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<String>): String? {
        return Gson().toJson(listOfString)
    }
}