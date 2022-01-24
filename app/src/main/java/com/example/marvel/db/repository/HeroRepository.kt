package com.example.marvel.db.repository

import android.content.Context
import com.example.marvel.db.database.HeroDataBase
import com.example.marvel.db.models.HeroData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class HeroRepository(context: Context) {

    private var db: HeroDataBase? = null

    init {
        db = HeroDataBase.getInstance(context)
    }

    fun addHero(hero: HeroData) {
        runBlocking(Dispatchers.IO) {
            db?.heroDao()?.addPerson(hero)
        }
    }

    fun getHeroById(id: String): HeroData? {
        return runBlocking(Dispatchers.IO) {
            db?.heroDao()?.getPerson(id)
        }
    }

    fun getHeroesList(): List<HeroData>? {
        return runBlocking(Dispatchers.IO) {
            try {
                db?.heroDao()?.getAllHeroes()
            } catch (ex: Exception) {
                ArrayList()
            }

        }
    }


}