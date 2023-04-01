package app.wac.team.base.ext

fun Any.getTagName(): Lazy<String> = lazy { this::class.java.simpleName }
