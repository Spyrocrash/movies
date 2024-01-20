package com.example.movies.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.data.Search
import com.squareup.picasso.Picasso

class CustomAdapter(private val dataSet: List<Search>, val mItemClickListener: ItemClickListener): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface ItemClickListener{
        fun onItemClick(id: String)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById<ImageView>(R.id.image_view_item)
        init {
            view.setOnClickListener{
                Log.d("testLog", dataSet.get(position).imdbID)
                dataSet.get(position).imdbID.let { it->mItemClickListener.onItemClick(it) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_model,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(dataSet.get(position).Poster).into(holder.imageView);
    }


}