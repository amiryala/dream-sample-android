package com.dream.sample.ui.home.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dream.sample.R
import kotlinx.android.synthetic.main.view_home_image_item.view.*

class HomeImageAdapter (var list: List<String> = emptyList()): RecyclerView.Adapter<HomeImageAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_home_image_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.itemView.apply {
            price_text_view.text = String.format("%s/%s", position.inc(), itemCount)
            Glide.with(this.context)
                .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_trulia_green))
                .load(item)
                .into(drawee_image_view)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}