package com.anthonychaufrias.test1.util

sealed class Resource<T> {

    class Success<T>(val data: T) : Resource<T>()

    class Error<T>(
        val message: UIText
    ) : Resource<T>()

}