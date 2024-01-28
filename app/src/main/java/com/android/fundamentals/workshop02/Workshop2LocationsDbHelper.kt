package com.android.fundamentals.workshop02

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Workshop2LocationsDbHelper(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Workshop2LocationsDbHelper.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${LocationsContract.LocationEntry.TABLE_NAME} " +
                    "(" +
                    "${LocationsContract.LocationEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${LocationsContract.LocationEntry.COLUMN_NAME_TITLE} TEXT," +
                    "${LocationsContract.LocationEntry.COLUMN_NAME_LATITUDE} REAL," +
                    "${LocationsContract.LocationEntry.COLUMN_NAME_LONGITUDE} REAL" +
                    ")"

        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${LocationsContract.LocationEntry.TABLE_NAME}"
    }
}