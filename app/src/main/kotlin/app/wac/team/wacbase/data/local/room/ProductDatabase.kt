package app.wac.team.wacbase.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import app.wac.team.wacbase.domain.entities.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDAO(): ProductDAO
}

object DbConstants {
    const val DATABASE_NAME = "product_db"
    const val PRODUCT_TABLE = "product_tb"
}