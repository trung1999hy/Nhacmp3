package com.tvt.nhac_mp3.netword

import com.tvt.nhac_mp3.netword.SongClient.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Song_retrofix {
    companion object {
        private val retrofit by lazy {

            val login = HttpLoggingInterceptor()
            login.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(login).build()
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()


        }
        val api: SongService by lazy {
            retrofit.create(SongService::class.java)
        }
    }
}