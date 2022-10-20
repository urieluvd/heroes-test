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
import com.ezamora.test.domain.Series
import timber.log.Timber

class SeriesRecyclerViewAdapter : ListAdapter<Series, SeriesRecyclerViewAdapter.SeriesViewHolder>(DiffCallback()) {

    class SeriesViewHolder(view : View): RecyclerView.ViewHolder(view){

        val cover =view.findViewById<ImageView>(R.id.cover)

        fun bind(series: Series){
            val thumbnail = series.thumbnail.toString()
            Timber.d("Thumbnail: $thumbnail")
            Glide.with(itemView.context)
                .load(thumbnail)
                .transition(DrawableTransitionOptions().crossFade())
                .into(cover)
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Series>() {

        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.equals(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.series_list_item, parent, false)
        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = getItem(position)
        holder.bind(series)
    }
}