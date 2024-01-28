package com.android.fundamentals.workshop03

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Ws03LocationsDao {
    @Query("SELECT * FROM locations_ws03 ORDER BY _id ASC")
	suspend fun getAll() : List<Ws03LocationEntity>

	@Insert
    suspend fun addLocation(location: Ws03LocationEntity)

    @Query("DELETE FROM locations_ws03 WHERE _id == :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT COUNT(_id) FROM locations_ws03")
    fun getLocationsCount(): LiveData<Int>
}