package com.example.marvel.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.db.models.Links

class UrlAdapter(private val urlsList: ArrayList<Links>, private val callback: (String) -> Unit) :
    RecyclerView.Adapter<UrlAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.urlLabel)
        val url: TextView = view.findViewById(R.id.url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.url_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = urlsList[position]
        holder.title.text = url.type
        holder.url.text = url.url
        holder.url.underline()

        holder.url.setOnClickListener {
            callback(url.url)
        }
    }

    private fun TextView.underline() {
        text = Html.fromHtml("<u>${text}</u>")
    }

    override fun getItemCount() = urlsList.size
}