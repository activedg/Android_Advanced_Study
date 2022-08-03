package com.example.naversearchtest.data.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseRepository {
    suspend inline fun <T> safeApiCall(crossinline callFunction: suspend () -> T): T? {
        return try {
            val myObject = withContext(Dispatchers.IO) { callFunction.invoke() }
            myObject
        } catch (e: Exception) {
//            withContext(Dispatchers.Main) {
//                e.printStackTrace()
//                Log.e("BaseRemoteRepo", "Call error: ${e.localizedMessage}", e.cause)
//                when(e){
//                    is HttpException -> {
//                        if(e.code() == 401) emitter.onError(ErrorType.SESSION_EXPIRED)
//                        else {
//                            val body = e.response()?.errorBody()
//                            emitter.onError(getErrorMessage(body))
//                        }
//                    }
//                    is SocketTimeoutException -> emitter.onError(ErrorType.TIMEOUT)
//                    is IOException -> emitter.onError(ErrorType.NETWORK)
//                    else -> emitter.onError(ErrorType.UNKNOWN)
//                }
//            }
            null
        }
    }
}