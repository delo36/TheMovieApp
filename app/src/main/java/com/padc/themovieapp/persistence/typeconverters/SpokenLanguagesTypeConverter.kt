package com.padc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.themovieapp.data.vos.ProductionCountriesVO
import com.padc.themovieapp.data.vos.SpokenLanguagesVO

class SpokenLanguagesTypeConverter {
    @TypeConverter
    fun toString(collection: List<SpokenLanguagesVO>?):String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toSpokenLanguagesVO(commentListJsonStr:String): List<SpokenLanguagesVO>?{
        val collectionVOType = object : TypeToken<List<SpokenLanguagesVO>?>(){}.type
        return Gson().fromJson(commentListJsonStr,collectionVOType)
    }
}