package app.wac.team.base.common

import java.lang.Exception

open class AppException(open var code: Int, override val message: String) : Exception(message)