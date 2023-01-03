package app.wac.team.wacbase.data.network.common

data class ErrorBody(
    val statusCode: Int,
    val error: String,
    val message: String,
)