package com.padc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverters {
    @TypeConverter
    fun toString(collection: List<Int>?):String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toGenreIdsVO(commentListJsonStr:String): List<Int>?{
        val collectionVOType = object : TypeToken<List<Int>?>(){}.type
        return Gson().fromJson(commentListJsonStr,collectionVOType)
    }
}