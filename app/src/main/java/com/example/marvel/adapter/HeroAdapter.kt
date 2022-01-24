package com.example.marvel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.db.models.HeroData
import com.squareup.picasso.Picasso

class HeroAdapter(private val heroesList: ArrayList<HeroData>, private val onItemClick: (HeroData) -> Unit) :
    RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = heroesList[position]
        Picasso.get().load(hero.thumbnail.portrait())
            .into(holder.image)


        holder.name.text = hero.name
        holder.description.text = hero.showDescription()
        holder.itemView.setOnClickListener {
            onItemClick(hero)
        }
    }

    override fun getItemCount() = heroesList.size

    class HeroViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val image: ImageView = mView.findViewById(R.id.heroImage)
        val name: TextView = mView.findViewById(R.id.heroName)
        val description: TextView = mView.findViewById(R.id.heroDescription)
    }

    fun update(newList: ArrayList<HeroData>) {
        val difUtilResult: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(MyDiffUtilCallback(heroesList, newList))
        heroesList.clear()
        heroesList.addAll(newList)
        difUtilResult.dispatchUpdatesTo(this)

    }
}