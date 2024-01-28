package com.android.fundamentals.workshop03

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Ws03LocationEntity::class], version = 2)
abstract class Ws03DataBase : RoomDatabase() {
    abstract val locationDao: Ws03LocationsDao

    companion object {
        fun create(applicationContext: Context): Ws03DataBase = Room.databaseBuilder(
            applicationContext,
            Ws03DataBase::class.java,
            Ws03DbContract.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}