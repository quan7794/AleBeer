package app.interview.ale.beer.data.network.common

data class ErrorBody(
    val statusCode: Int,
    val error: String,
    val message: String,
)