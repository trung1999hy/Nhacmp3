package com.tvt.nhac_mp3

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tvt.nhac_mp3.adapter.SongAdapter
import com.tvt.nhac_mp3.databinding.ActivityMainBinding
import com.tvt.nhac_mp3.model.Song
import com.tvt.nhac_mp3.netword.Onclick
import com.tvt.nhac_mp3.netword.SongClient.BASE_URL
import com.tvt.nhac_mp3.netword.Song_retrofix
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), Onclick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var songAdapter: SongAdapter

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setRectcelview()
    }

    private fun setRectcelview() {
        songAdapter = SongAdapter(this)
        binding.rvMusic.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            adapter = songAdapter
            addItemDecoration(object :
                DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL) {})
        }
        setData()
    }

    private fun setData() {
        Song_retrofix.api.getDataSong().enqueue(object : retrofit2.Callback<Song> {
            override fun onResponse(call: Call<Song>, response: Response<Song>) {
                if (response.isSuccessful) {
                    val music = response.body()?.music
                    songAdapter.differ.submitList(music)
                }
            }

            override fun onFailure(call: Call<Song>, t: Throwable) {
                Toast.makeText(this@MainActivity, "lỗi", Toast.LENGTH_SHORT).show()
            }

        })


    }

    private fun startMusic(positiont: Int) {
        mediaPlayer = MediaPlayer()
        var url =BASE_URL+songAdapter.differ.currentList[positiont].source

        mediaPlayer !!.setDataSource(url)
        mediaPlayer !!.prepare()
        mediaPlayer !!.start()

    }


    override fun itemOnClick(position: Int) {
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()

            startMusic(position)
        }else{
            startMusic(position)
        }
    }
}


