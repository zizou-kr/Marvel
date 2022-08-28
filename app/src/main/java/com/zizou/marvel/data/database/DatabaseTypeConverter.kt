package com.zizou.marvel.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.zizou.marvel.data.model.DataListEntity
import com.zizou.marvel.data.model.ImageEntity
import com.zizou.marvel.data.model.UrlEntity

class DatabaseTypeConverter {
    @TypeConverter
    fun convertUrlsToJson(entity: List<UrlEntity>): String {
        return Gson().toJson(entity)
    }

    @TypeConverter
    fun convertJsonToUrls(json: String): List<UrlEntity>? {
        return Gson().fromJson(json, Array<UrlEntity>::class.java)?.toList()
    }

    @TypeConverter
    fun convertDataListToJson(entity: DataListEntity): String {
        return Gson().toJson(entity)
    }

    @TypeConverter
    fun convertJsonToDataList(json: String): DataListEntity? {
        return Gson().fromJson(json, DataListEntity::class.java)
    }

    @TypeConverter
    fun convertImageToJson(entity: ImageEntity): String {
        return Gson().toJson(entity)
    }

    @TypeConverter
    fun convertJsonToImage(json: String): ImageEntity? {
        return Gson().fromJson(json, ImageEntity::class.java)
    }
}
