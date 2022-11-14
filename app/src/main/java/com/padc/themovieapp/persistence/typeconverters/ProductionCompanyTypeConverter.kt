package com.padc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.themovieapp.data.vos.GenreVO
import com.padc.themovieapp.data.vos.ProductionCompaniesVO

class ProductionCompanyTypeConverter {
    @TypeConverter
    fun toString(collection: List<ProductionCompaniesVO>?):String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toProductionCompanies(commentListJsonStr:String): List<ProductionCompaniesVO>?{
        val collectionVOType = object : TypeToken<List<ProductionCompaniesVO>?>(){}.type
        return Gson().fromJson(commentListJsonStr,collectionVOType)
    }
}