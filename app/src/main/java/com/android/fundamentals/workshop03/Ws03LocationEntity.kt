package com.android.fundamentals.workshop03

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = Ws03DbContract.Locations.TABLE_NAME,
	indices = [Index(Ws03DbContract.Locations.COLUMN_NAME_ID)])
data class Ws03LocationEntity(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = Ws03DbContract.Locations.COLUMN_NAME_ID)
	val id: Long = 0,

	@ColumnInfo(name = Ws03DbContract.Locations.COLUMN_NAME_TITLE)
	val title: String,

	@ColumnInfo(name = Ws03DbContract.Locations.COLUMN_NAME_LATITUDE)
	val lat: Double,

	@ColumnInfo(name = Ws03DbContract.Locations.COLUMN_NAME_LONGITUDE)
	val lon: Double
)