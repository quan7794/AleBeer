package app.wac.team.wacbase.base

import io.reactivex.rxjava3.core.Single

interface IUseCase<Type> {
    fun execute(): Single<Type>
}