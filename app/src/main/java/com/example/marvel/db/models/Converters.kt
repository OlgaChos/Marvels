package com.example.marvel.db.models

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun  fromString(value: String): ArrayList<Links> {
            val typeToken = object : TypeToken<ArrayList<Links>>() {}.type
            val list = GsonBuilder().create().fromJson<ArrayList<Links>>(value, typeToken)
            return list ?: ArrayList()
        }

        @TypeConverter
        @JvmStatic
        fun fromArrayList(value: ArrayList<Links>?): String {
            val json = GsonBuilder().create().toJson(value)
            return json ?: ""
        }
    }
}
