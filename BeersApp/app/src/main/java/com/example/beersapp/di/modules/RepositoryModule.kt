package com.example.beersapp.di.modules

import com.example.beersapp.entity.beer.BeerRepository
import com.example.beersapp.room.BeerDB
import com.example.beersapp.room.model.room.RoomBeerDataMaper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class RepositoryModule {

    @Provides
    fun beerRepositoryProvider(
        retrofit: Retrofit,
        db : BeerDB,
        roomBeerDataMaper : RoomBeerDataMaper
    ) = BeerRepository(retrofit, db, roomBeerDataMaper)
}