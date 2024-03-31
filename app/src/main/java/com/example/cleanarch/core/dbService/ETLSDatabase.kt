package com.example.cleanarch.core.dbService

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ETLSDatabase : RoomDatabase(){

    abstract fun eTlsDAO() : ETLSDAO

}