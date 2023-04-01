package app.wac.team.wacbase.di.module

import android.content.Context
import androidx.room.Room
import app.wac.team.wacbase.data.local.room.DbConstants.DATABASE_NAME
import app.wac.team.wacbase.data.local.room.ProductDatabase
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
        ProductDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ProductDatabase) = database.productDAO()
}