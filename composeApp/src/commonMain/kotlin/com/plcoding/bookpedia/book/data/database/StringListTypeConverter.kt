package com.plcoding.bookpedia.book.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object StringListTypeConverter {

    @TypeConverter
    fun formString(value: String): List<String> = Json.decodeFromString(value)

    @TypeConverter
    fun formList(list: List<String>): String = Json.encodeToString(list)
}