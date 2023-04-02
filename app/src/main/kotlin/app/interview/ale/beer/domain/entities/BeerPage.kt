package app.interview.ale.beer.domain.entities


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import app.interview.ale.beer.data.local.room.DbConstants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

data class BeerPage(
    @SerializedName("data")
    val beers: List<Beer>,
    @SerializedName("loadMore")
    val loadMore: Boolean = false,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("total")
    val total: Int = -1,
)

@Entity(tableName = DbConstants.BEER_TABLE)
@Parcelize
data class Beer(
    @PrimaryKey
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val imageUrl: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("price")
    val price: String = "",
    @SerializedName("rating")
    val rating: Rating = Rating(),
    @SerializedName("sale_off_time")
    val saleOffTime: Long = -1,
    @SerializedName("note")
    val note: String = "",
) : Parcelable

@Parcelize
data class Rating(
    @SerializedName("average")
    val average: Double = -1.0,
    @SerializedName("reviews")
    val reviews: Int = -1,
) : Parcelable

class RatingConverter {
    @TypeConverter
    fun fromString(value: String): Rating {
        val jsonObj = JSONObject(value)
        val avg = jsonObj.getDouble("average")
        val reviews = jsonObj.getInt("reviews")
        return Rating(avg, reviews)
    }

    @TypeConverter
    fun toString(rating: Rating): String {
        val jsonObj = JSONObject()
        jsonObj.put("average", rating.average)
        jsonObj.put("reviews", rating.reviews)
        return jsonObj.toString()
    }
}