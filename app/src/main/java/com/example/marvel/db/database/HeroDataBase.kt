package com.example.marvel.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marvel.db.models.HeroData
import com.example.marvel.db.dao.HeroDao
import com.example.marvel.db.models.Converters

@Database(entities = [HeroData::class], version = 1)
@TypeConverters(Converters::class)
abstract class HeroDataBase : RoomDatabase() {

    abstract fun heroDao(): HeroDao

    companion object {
        private var INSTANCE: HeroDataBase? = null

        fun getInstance(context: Context): HeroDataBase? {
            if (INSTANCE == null) {
                synchronized(HeroDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        HeroDataBase::class.java, "heroDataBase"
                    ).build()
                }
            }
            return INSTANCE
        }

    }


}