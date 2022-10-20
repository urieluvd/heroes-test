package com.ezamora.test.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ezamora.test.R
import com.ezamora.test.domain.Comic
import timber.log.Timber

class ComicsRecyclerViewAdapter : ListAdapter<Comic, ComicsRecyclerViewAdapter.ComicViewHolder>(DiffCallback()) {

    class ComicViewHolder(view : View): RecyclerView.ViewHolder(view){

        val cover =view.findViewById<ImageView>(R.id.cover)

        fun bind(comic: Comic){
            val thumbnail = comic.thumbnail.toString()
            Timber.d("Thumbnail: $thumbnail")
            Glide.with(itemView.context)
                .load(thumbnail)
                .transition(DrawableTransitionOptions().crossFade())
               .into(cover)
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Comic>() {

        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.equals(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.comic_list_item, parent, false)
        return ComicViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = getItem(position)
        holder.bind(comic)
    }
}