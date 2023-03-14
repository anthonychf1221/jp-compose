package com.anthonychaufrias.test1.data.network

import com.anthonychaufrias.test1.data.model.ErrorResponse
import com.anthonychaufrias.test1.util.ErrorMessageCode
import com.anthonychaufrias.test1.util.Resource
import com.anthonychaufrias.test1.util.UIText
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.*

abstract class BaseService {

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful) {
                    Resource.Success(data = response.body()!!)
                }
                else {
                    val errorResponse: ErrorResponse? = convertErrorBody(response.errorBody())

                    val code = errorResponse?.code ?: "GeneralException"
                    Resource.Error(
                        UIText.ErrorMessageCodeResource(code = ErrorMessageCode.valueOf(
                            code.uppercase(Locale.ROOT)
                        ))
                    )
                }

            } catch (e: HttpException) {
                Resource.Error(
                    UIText.ErrorMessageCodeResource(code = ErrorMessageCode.HTTPEXCEPTION)
                )
            } catch (e: IOException) {
                Resource.Error(
                    UIText.ErrorMessageCodeResource(code = ErrorMessageCode.IOEXCEPTION)
                )
            } catch (e: Exception) {
                Resource.Error(
                    UIText.ErrorMessageCodeResource(code = ErrorMessageCode.GENERALEXCEPTION)
                )
            }
        }
    }

    private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
        return try {
            Gson().fromJson(errorBody?.string(), ErrorResponse::class.java)
        } catch (exception: Exception) {
            null
        }
    }
}