package com.testapp.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

object AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("Authorization", "Bearer $TOKEN")

        return chain.proceed(requestBuilder.build())
    }

    private const val TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMTMxMWM4OTg4NzE3MjJiNTA5MWE0NjJkMGJkMjI1MiIsIm5iZiI6MTcyMjcwOTAzOS45NTU0Nywic3ViIjoiNWY3NzZkNzJkZDQ3MTYwMDM3MTllMzk2Iiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.cRqKNoigbsUNUU60t-ijbjLD8FYzYf-AC-1DTdpaPtY"
}