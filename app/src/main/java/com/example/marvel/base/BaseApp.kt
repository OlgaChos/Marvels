package com.example.marvel.base

import android.app.Application
import com.example.marvel.db.database.HeroDataBase

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        HeroDataBase.getInstance(this)
    }
}