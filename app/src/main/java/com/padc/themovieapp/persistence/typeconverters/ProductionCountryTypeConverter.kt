package com.padc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.themovieapp.data.vos.ProductionCompaniesVO
import com.padc.themovieapp.data.vos.ProductionCountriesVO

class ProductionCountryTypeConverter {
    @TypeConverter
    fun toString(collection: List<ProductionCountriesVO>?):String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toProductionCountriesVO(commentListJsonStr:String): List<ProductionCountriesVO>?{
        val collectionVOType = object : TypeToken<List<ProductionCountriesVO>?>(){}.type
        return Gson().fromJson(commentListJsonStr,collectionVOType)
    }
}