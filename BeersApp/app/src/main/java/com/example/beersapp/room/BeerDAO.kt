package com.example.beersapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.beersapp.room.model.room.RoomBeer

@Dao
interface BeerDAO {

    @Query("SELECT * FROM RoomBeer")
    suspend fun getBeers() : List<RoomBeer>

    @Insert
    suspend fun saveBeers(list: List<RoomBeer>)

    @Query("DELETE FROM RoomBeer")
    suspend fun deleteAllBeers()

}