package com.anthonychaufrias.test1.util

import android.content.Context
import com.anthonychaufrias.test1.R

fun UIText.asString(context: Context): String{
    return when(this){
        is UIText.ErrorMessageCodeResource -> context.getString(
            getErrorResourceIdBy(code)
        )
        is UIText.SuccessMessageCodeResource -> context.getString(
            getSuccessResourceIdBy(code)
        )
        is UIText.StringValue -> value
        else -> context.getString(R.string.general_exception_message)
    }
}

private fun getErrorResourceIdBy(code: ErrorMessageCode): Int{
    return when(code){
        ErrorMessageCode.GENERALEXCEPTION -> R.string.general_exception_message
        ErrorMessageCode.INVALIDEMAIL -> R.string.invalid_email
        ErrorMessageCode.HTTPEXCEPTION -> R.string.http_exception_message
        ErrorMessageCode.IOEXCEPTION -> R.string.io_exception_message
        else -> R.string.general_exception_message
    }
}

private fun getSuccessResourceIdBy(code: SuccessMessageCode): Int{
    return when(code){
        SuccessMessageCode.SIGNUPSUCCESSFULLY -> R.string.signup_successfully_message
        else -> R.string.general_success_message
    }
}