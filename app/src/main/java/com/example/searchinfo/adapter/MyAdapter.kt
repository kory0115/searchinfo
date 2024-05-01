package com.example.searchinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.searchinfo.databinding.ViewholderMyBinding
import com.example.searchinfo.room.RoomEntity

class MyAdapter: ListAdapter<RoomEntity, MyAdapter.ItemViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RoomEntity>() {
            override fun areItemsTheSame(oldItem: RoomEntity, newItem: RoomEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: RoomEntity, newItem: RoomEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ViewholderMyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ItemViewHolder(private val binding: ViewholderMyBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(roomEntity: RoomEntity) {
            binding.mainImage.load(roomEntity.thumbnailurl)
            binding.mainTextView.text = roomEntity.collection
            binding.timeTextView.text = roomEntity.datetime
        }
    }
}