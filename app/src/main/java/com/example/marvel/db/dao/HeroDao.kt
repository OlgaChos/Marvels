package com.example.marvel.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvel.db.models.HeroData

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPerson(hero: HeroData)

    @Query("select  * From hero where id = :id")
    suspend fun getPerson(id: String): HeroData

    @Query("select * From hero")
    suspend fun getAllHeroes(): List<HeroData>

}