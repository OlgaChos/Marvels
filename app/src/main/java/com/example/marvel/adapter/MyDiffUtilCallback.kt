package com.example.marvel.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.marvel.db.models.HeroData

open class MyDiffUtilCallback(
    private val oldList: List<HeroData>,
    private val newList: List<HeroData>
) :
    DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

}
