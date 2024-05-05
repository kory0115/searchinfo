package com.example.searchinfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.searchinfo.data.ImageEntity
import com.example.searchinfo.databinding.ViewholderHomeBinding
import com.example.searchinfo.preference.SharedPreferences

class HomeAdapter(val context: Context, val onClick : (Pair<ImageEntity, Boolean>) -> Unit): ListAdapter<ImageEntity, HomeAdapter.ViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ImageEntity>() {
            override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val prefer by lazy { SharedPreferences(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ViewholderHomeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(imageEntity: ImageEntity) {
            binding.mainImage.load(imageEntity.thumbnailurl)
            binding.mainTextView.text = imageEntity.collection
            binding.timeTextView.text = imageEntity.datetime

            binding.like.isVisible = prefer.loadLike(imageEntity.thumbnailurl)

            binding.root.setOnClickListener {

                binding.like.isVisible = !binding.like.isVisible
                if(binding.like.isVisible) {
                    onClick(Pair(imageEntity, true))
                } else {
                    onClick(Pair(imageEntity, false))
                }
            }
        }
    }
}