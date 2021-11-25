package com.tvt.nhac_mp3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tvt.nhac_mp3.MainActivity
import com.tvt.nhac_mp3.databinding.ActivityMainBinding
import com.tvt.nhac_mp3.databinding.ActivitySongBinding
import com.tvt.nhac_mp3.model.Music
import com.tvt.nhac_mp3.netword.Onclick

import com.tvt.nhac_mp3.netword.SongClient.BASE_URL

class SongAdapter(var click: Onclick) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    private var binding: ActivitySongBinding? = null


    private val callback = object : DiffUtil.ItemCallback<Music>() {
        override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
            return oldItem.source == newItem.source
        }

        override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongAdapter.ViewHolder {
        binding = ActivitySongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding!!)

    }

    override fun onBindViewHolder(holder: SongAdapter.ViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class ViewHolder(private var binding: ActivitySongBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(music: Music) {
            binding.tvAlbum.text = music.album
            binding.tvTitle.text = music.title
            Glide.with(binding.ivItemImage).load(BASE_URL + music.image).into(binding.ivItemImage)
            binding.tvPlay.setOnClickListener {
                click.itemOnClick(adapterPosition)

            }
        }

    }
}