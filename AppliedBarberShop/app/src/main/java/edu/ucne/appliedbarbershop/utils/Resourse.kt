package edu.ucne.appliedbarbershop.utils

sealed class Resourse<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : Resourse<T>(data)
    class Success<T>(data: T) : Resourse<T>(data)
    class Error<T>(message: String, data: T? = null) : Resourse<T>(data, message)
}
