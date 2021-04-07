package com.jvillad1.marsrover.data.commons

sealed class Output<out T> {

    data class Success<out T>(val data: T) : Output<T>()
    data class Error(val errorMessage: String) : Output<Nothing>()

    companion object {

        fun <T> success(data: T): Output<T> =
            Success(data)

        fun error(errorMessage: String): Output<Nothing> =
            Error(errorMessage)
    }
}

inline fun <reified T> Output<T>.successOrNull() : T? = if (this is Output.Success<T>) {
    this.data
} else {
    null
}
