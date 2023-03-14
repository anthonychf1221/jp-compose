package com.anthonychaufrias.test1.util

sealed class UIText {

    data class StringValue(val value: String): UIText()

    class ErrorMessageCodeResource(
        val code: ErrorMessageCode
    ): UIText()

    class SuccessMessageCodeResource(
        val code: SuccessMessageCode
    ): UIText()

}

enum class ErrorMessageCode{
    GENERALEXCEPTION, INVALIDEMAIL, HTTPEXCEPTION, IOEXCEPTION
}

enum class SuccessMessageCode{
    SIGNUPSUCCESSFULLY
}