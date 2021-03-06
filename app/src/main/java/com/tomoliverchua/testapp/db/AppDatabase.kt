package com.tomoliverchua.testapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tomoliverchua.testapp.utils.DATABASE_NAME
import com.tomoliverchua.testapp.db.dao.*
import com.tomoliverchua.testapp.models.*


@Database(
    entities = [AirpotDetailsEntity::class, CityEntity::class, CountryEntity::class, LocationEntity::class, RegionEntity::class],
    version = 1,
    exportSchema = false
)
 abstract class AppDatabase : RoomDatabase() {

    abstract fun airportDetailsDao(): airportDetailsDao
    abstract fun cityDao(): cityDao
    abstract fun countryDao(): countryDao
    abstract fun locationDao(): locationDao
    abstract fun regionDao(): regionDao

    companion object {

        // For singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }

    }
}