package com.android.fundamentals.workshop03

import android.content.Context
import com.android.fundamentals.domain.location.Location
import com.android.fundamentals.domain.location.LocationRepository
import com.android.fundamentals.workshop02_03.NewLocationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

class Ws03LocationsRepository(applicationContext: Context) : LocationRepository {
	
	private val random = Random(10)
	

	private val database = Ws03DataBase.create(applicationContext)

	private fun toEntity(location: NewLocationRequest) = Ws03LocationEntity(
		title = location.title,
		lat = location.latitude,
		lon = location.longitude
	)

	private fun toLocation(entity: Ws03LocationEntity) = Location(
		id = entity.id,
		title = "${entity.title}, id:${entity.id}",
		latitude = entity.lat,
		longitude = entity.lon
	)

	private suspend fun generateNewLocationRequest() = withContext(Dispatchers.Default) {
		delay(DELAY_MILLIS)
		NewLocationRequest(
			title = "Title ${random.nextInt()}",
			latitude = random.nextDouble(180.0),
			longitude = random.nextDouble(180.0)
		)
	}

	companion object {
		private const val DELAY_MILLIS: Long = 1_000
	}

	override suspend fun getAllLocations(): List<Location> = withContext(Dispatchers.IO) {
		database.locationDao.getAll().map{toLocation(it)}
	}

	override suspend fun addNewAndGetUpdated(): List<Location> = withContext(Dispatchers.IO) {
		database.locationDao.addLocation(toEntity(generateNewLocationRequest()))
		getAllLocations()
	}

	override suspend fun deleteByIdAndGetUpdated(id: Long): List<Location> = withContext(Dispatchers.IO) {
		database.locationDao.deleteById(id)
		getAllLocations()
	}
}
