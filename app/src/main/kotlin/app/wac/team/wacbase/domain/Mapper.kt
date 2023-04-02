package app.wac.team.wacbase.domain

interface Mapper<in LeftObject, out RightObject> {

    fun LeftObject.mapLeftToRight(): RightObject

}