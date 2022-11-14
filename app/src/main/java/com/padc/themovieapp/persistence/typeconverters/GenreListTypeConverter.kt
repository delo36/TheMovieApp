package com.padc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.themovieapp.data.vos.CollectionVO
import com.padc.themovieapp.data.vos.GenreVO

class GenreListTypeConverter {
    @TypeConverter
    fun toString(collection: List<GenreVO>?):String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toGenreList(commentListJsonStr:String): List<GenreVO>?{
        val collectionVOType = object : TypeToken<List<GenreVO>?>(){}.type
        return Gson().fromJson(commentListJsonStr,collectionVOType)
    }
}