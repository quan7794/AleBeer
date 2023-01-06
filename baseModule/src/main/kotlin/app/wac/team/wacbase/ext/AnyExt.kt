package app.wac.team.wacbase.ext

fun Any.getTagName(): Lazy<String> = lazy { this::class.java.simpleName }
