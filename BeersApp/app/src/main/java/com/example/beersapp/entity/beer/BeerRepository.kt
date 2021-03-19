package com.example.beersapp.entity.beer
import com.example.beersapp.api.BeerApi
import com.example.beersapp.room.BeerDB
import com.example.beersapp.room.model.room.RoomBeerDataMaper
import retrofit2.Retrofit

class BeerRepository (
    private var retrofit: Retrofit,
    private var db : BeerDB,
    private var roomBeerDataMaper: RoomBeerDataMaper
){

    suspend fun getBeers(page : Int) : List<Beer>{
        return retrofit.create(BeerApi.GetBeers::class.java).getBeers(page)
    }

    suspend fun getCachedBeers()  : List<Beer>{
        return roomBeerDataMaper.roomBeerListToRoomBeerList(db.wordDefinitionDAO().getBeers())
    }

    suspend fun deleteAllLocalBeers(){
        db.wordDefinitionDAO().deleteAllBeers()
    }

    suspend fun saveBeersInCache(beers : List<Beer>){
        db.wordDefinitionDAO().saveBeers(roomBeerDataMaper.beerListToRoomBeerList(beers))
    }
}