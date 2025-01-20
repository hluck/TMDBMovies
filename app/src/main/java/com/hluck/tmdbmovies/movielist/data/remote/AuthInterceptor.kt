package com.hluck.tmdbmovies.movielist.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        val requestBuilder: Request.Builder = originalRequest.newBuilder()
            .header("Authorization", MoviesApi.TOKEN)

        val request: Request = requestBuilder.build()
        val response = chain.proceed(request)
        // 使用 peekBody 安全读取响应体
//        val jsonString = response.peekBody(Long.MAX_VALUE).string()

        return response
    }
}