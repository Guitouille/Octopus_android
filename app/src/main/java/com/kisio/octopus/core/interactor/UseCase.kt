package com.kisio.octopus.core.interactor

import com.kisio.octopus.core.exception.Failure
import com.kisio.octopus.core.functional.Either
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    fun execute(onResult: (Either<Failure, Type>) -> Unit, params: Params) {
        val job = async(CommonPool) { run(params) }
        launch(UI) { onResult.invoke(job.await()) }
    }

    class None
}
