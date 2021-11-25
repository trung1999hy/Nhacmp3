package com.tvt.nhac_mp3.netword

import android.telecom.Call
import com.tvt.nhac_mp3.model.Song
import retrofit2.http.GET

interface SongService {
    @GET("music.json")
    fun getDataSong():retrofit2.Call<Song>
}