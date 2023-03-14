package com.anthonychaufrias.test1.util

sealed class UserInterface<T> {

    class Loading<T>(): UserInterface<T>()

    class Data<T>(val data: T) : UserInterface<T>()

}