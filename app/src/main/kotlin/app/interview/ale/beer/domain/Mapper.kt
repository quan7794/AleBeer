package app.interview.ale.beer.domain

interface Mapper<in LeftObject, out RightObject> {

    fun LeftObject.mapLeftToRight(): RightObject

}