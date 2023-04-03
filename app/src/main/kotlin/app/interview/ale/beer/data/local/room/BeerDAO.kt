package app.interview.ale.beer.data.local.room

import androidx.room.*
import app.interview.ale.beer.data.local.room.DbConstants.BEER_TABLE
import app.interview.ale.beer.domain.entities.Beer
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDAO {
    @Query("SELECT * FROM $BEER_TABLE ORDER BY id ASC")
    fun getAll(): Flow<List<Beer>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(beer: Beer)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(beer: Beer): Int

    @Delete
    suspend fun delete(beer: Beer): Int

    @Query("SELECT EXISTS(SELECT * FROM $BEER_TABLE WHERE id = :beerId)")
    suspend fun isExist(beerId: Int): Boolean

    @Query("SELECT * FROM $BEER_TABLE WHERE id = :beerId")
    suspend fun returnIfExist(beerId: Int): Beer?
}