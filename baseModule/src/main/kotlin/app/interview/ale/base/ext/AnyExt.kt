package app.interview.ale.base.ext

fun Any.getTagName(): Lazy<String> = lazy { this::class.java.simpleName }
