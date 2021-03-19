package com.example.beersapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.beersapp.room.model.room.RoomBeerDataMaper
import dagger.Module
import dagger.Provides
import com.example.beersapp.room.BeerDB

@Module
class LocalStorageModule {

    @Provides
    fun beerLocalDatabasePrivider(context: Context) = Room.databaseBuilder(
            context,
            BeerDB::class.java, "beer-db"
        ).build()

    @Provides
    fun roomBeerDataMapperProvider() = RoomBeerDataMaper()
}