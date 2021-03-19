package com.example.beersapp.room.model.room

import com.example.beersapp.entity.beer.Beer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomBeerDataMaper {

    fun beerToRoomBeer(beer: Beer) : RoomBeer {
        return RoomBeer(
            beer.id,
            beer.name,
            beer.image,
            beer.tagline,
            beer.description,
            beer.firstBrewed,
            Gson().toJson(beer.foodPairing),
        )
    }

    fun roomBeerToBeer(beer: RoomBeer) :  Beer{
        return Beer(
            beer.id,
            beer.name,
            beer.image,
            beer.tagline,
            beer.description,
            beer.firstBrewed,
            Gson().fromJson(beer.foodPairing, object : TypeToken<List<String?>?>() {}.type)
        )
    }
    fun beerListToRoomBeerList(productList: List<Beer>) : List<RoomBeer>{
        return productList.map(this::beerToRoomBeer)
    }

    fun roomBeerListToRoomBeerList(productList: List<RoomBeer>) :  List<Beer>{
        return productList.map(this::roomBeerToBeer)
    }
}