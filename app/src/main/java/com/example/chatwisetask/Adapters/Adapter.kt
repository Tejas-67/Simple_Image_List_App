package com.example.chatwisetask.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatwisetask.DataModel.ImageItem
import com.example.chatwisetask.R

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    class ImageViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val image: ImageView =view.findViewById(R.id.imageView)
    }

    private val differCallback=object: DiffUtil.ItemCallback<ImageItem>(){
        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem==newItem
        }
    }

    val differ= AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item=differ.currentList[position]
        Glide.with(holder.itemView.context).load(item.url).into(holder.image)
    }
}