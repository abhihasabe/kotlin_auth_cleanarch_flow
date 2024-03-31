package com.example.cleanarch.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarch.core.dbService.Converters
import com.example.cleanarch.core.dbService.ETLSDAO
import com.example.cleanarch.core.dbService.ETLSDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesShopDatabase(app : Application,gson : Gson) : ETLSDatabase {
        return Room.databaseBuilder(app, ETLSDatabase::class.java,"shop_database")
            .fallbackToDestructiveMigration()
            .addTypeConverter(Converters(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesFlyBuyDAO(database: ETLSDatabase) : ETLSDAO {
        return database.eTlsDAO()
    }

}