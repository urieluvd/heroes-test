package com.ezamora.test.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ezamora.test.R
import com.ezamora.test.databinding.HeroesItemBinding

class HeroesAdapter(var listener: HeroesAdapterListener) : RecyclerView.Adapter<HeroesAdapter.ViewHolder>() {
    var heroesList : ArrayList<com.ezamora.test.domain.Character> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.heroes_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = heroesList[position]
        holder.binding.txtHeroName.text = listItem.name
        if (listItem.description != ""){
            holder.binding.txtHeroDescription.text = listItem.description
        }

        Glide.with(holder.binding.imgHero.context)
            .load(listItem.thumbnail.toString())
            .transition(DrawableTransitionOptions().crossFade())
            .into(holder.binding.imgHero)

        holder.itemView.setOnClickListener(View.OnClickListener {
            listener.onHeroClicked(listItem)
        })
    }

    override fun getItemCount(): Int {
        return heroesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = HeroesItemBinding.bind(itemView)
    }
}