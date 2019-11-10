package com.jayeshsolanki.recipeapp.tools

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager {

    private const val CACHE_SIZE = 50 * 1024 * 1024L // 50 MB

    private fun getHttpCache(context: Context) = Cache(context.cacheDir, CACHE_SIZE)

    private fun okHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(StethoInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    internal fun retrofit(context: Context, baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl(baseUrl)
                .client(okHttpClient(getHttpCache((context))))
                .build()
    }
}
