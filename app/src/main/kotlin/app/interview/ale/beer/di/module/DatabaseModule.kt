package app.interview.ale.beer.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import app.interview.ale.beer.data.local.room.DbConstants.DATABASE_NAME
import app.interview.ale.beer.data.local.room.BeerDataBase
import app.interview.ale.beer.data.local.room.DbConstants.BEER_TABLE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private val migration1to2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE $BEER_TABLE ADD COLUMN lastSaved LONG NOT NULL DEFAULT 0")
        }
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BeerDataBase::class.java, DATABASE_NAME)
            .addMigrations(migration1to2)
            .build()

    @Singleton
    @Provides
    fun provideDao(database: BeerDataBase) = database.bearDAO()
}