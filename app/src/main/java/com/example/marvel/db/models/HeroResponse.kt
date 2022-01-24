package com.example.marvel.db.models

import androidx.room.*

data class HeroResponse(

    val code: Int,
    val data: Data
)

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<HeroData>
)

@Entity(tableName = "hero")
@TypeConverters(Converters::class)
data class HeroData(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val description: String,
    @Embedded
    val thumbnail: ImageData,
    val urls: ArrayList<Links> = ArrayList()
) {
    fun showDescription(): String {
        return if (description.isNotEmpty()) {
            description
        } else {
            "it looks like there is no information about this character in the official Marvel page," +
                    " but for the general background we will fill this part of the text for design"
        }
    }
}

data class ImageData(
    val path: String,
    val extension: String
) {
    fun portrait(): String {
        return "$path/portrait_incredible.$extension"
    }
}

data class Links(
    @ColumnInfo(name = "link_type")
    val type: String,
    @ColumnInfo(name = "link_url")
    val url: String
)