package com.tvt.nhac_mp3.model

data class Song(val music:List<Music>
)
data class Music(
    val album:String,
    val image:String,
    val title:String,
    val source:String,
)