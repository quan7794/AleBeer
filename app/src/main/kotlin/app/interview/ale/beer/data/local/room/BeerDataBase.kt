package app.interview.ale.beer.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.interview.ale.beer.domain.entities.Beer
import app.interview.ale.beer.domain.entities.RatingConverter

@Database(entities = [Beer::class], version = 1, exportSchema = false)
@TypeConverters(RatingConverter::class)
abstract class BeerDataBase : RoomDatabase() {
    abstract fun bearDAO(): BeerDAO
}

object DbConstants {
    const val DATABASE_NAME = "beer_db"
    const val BEER_TABLE = "beer_tb"
}