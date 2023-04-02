package app.wac.team.wacbase.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.wac.team.wacbase.data.local.room.DbConstants.PRODUCT_TABLE

@Entity(tableName = PRODUCT_TABLE)
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)
