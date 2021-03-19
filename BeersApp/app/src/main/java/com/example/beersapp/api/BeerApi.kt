package com.example.beersapp.api

import com.example.beersapp.entity.beer.Beer
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {
    interface GetBeers{
        @GET("beers")
        suspend fun getBeers(@Query("page") page : Int) :  ArrayList<Beer>
    }
}