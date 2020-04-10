package com.dream.sample.ui.home.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dream.sample.R
import com.dream.sample.data.model.Home
import kotlinx.android.synthetic.main.view_home_listing_item.view.*
import java.text.NumberFormat

class HomeListingAdapter (val list: MutableList<Home> = mutableListOf(),
                          val onItemClickListener: (Home) -> Unit) : RecyclerView.Adapter<HomeListingAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_home_listing_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.itemView.apply {
            setOnClickListener { onItemClickListener(item) }

            price_text_view.text = NumberFormat.getCurrencyInstance().format(item.price)

            bath_text_view.text = String.format("%.1f bd ", item.numberOfBathroom)
            bed_text_view.text = String.format("%s ba ", item.numberOfBedroom)
            square_ft_textview.text = String.format("%s sq ft ", item.squareFeet)

            listing_type_text_view.text = item.listingType

            street_textview.text = String.format("%s %s", item.streetNumber, item.streetName)
            city_state_text_view.text = String.format("%s, %s, %s", item.city, item.stateCode, item.zipCode)

            image_count_text_view.text = item.numberOfPhotos.toString()

            item.photos?.firstOrNull()?.let {url ->
                Glide.with(this.context)
                    .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_trulia_green))
                    .load(url)
                    .into(drawee_image_view)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}