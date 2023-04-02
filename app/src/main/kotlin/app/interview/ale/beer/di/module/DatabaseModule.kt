package app.interview.ale.beer.di.module

import android.content.Context
import androidx.room.Room
import app.interview.ale.beer.data.local.room.DbConstants.DATABASE_NAME
import app.interview.ale.beer.data.local.room.BeerDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        BeerDataBase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: BeerDataBase) = database.bearDAO()
}