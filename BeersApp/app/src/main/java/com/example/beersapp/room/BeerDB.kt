package com.example.beersapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.beersapp.room.model.room.RoomBeer

@Database(
    entities = [RoomBeer::class],
    version = 1
)
abstract class BeerDB : RoomDatabase(){
    abstract fun wordDefinitionDAO() : BeerDAO
}