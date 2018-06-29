package com.kisio.octopus.features.restaurants.presentation

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kisio.octopus.R
import com.kisio.octopus.features.restaurant.presentation.RestaurantDetailsActivity
import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_restaurant.*
import javax.inject.Inject
import kotlin.properties.Delegates

class RestaurantsAdapter @Inject constructor() : RecyclerView.Adapter<RestaurantsAdapter.RestaurantHolder>() {

    internal var restaurantList: MutableList<RestaurantEntity> by Delegates.observable(mutableListOf()) {
        _,_ , _ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHolder =
            RestaurantItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_restaurant, parent, false))


    override fun getItemCount(): Int {
        return restaurantList.size
    }

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {
        if (position < restaurantList.size) {
            (holder as RestaurantItemHolder).bind(restaurantList[position])
        }
    }

    fun setData(restaurants: List<RestaurantEntity>) {
        restaurantList.clear()
        restaurantList.addAll(restaurants)
        notifyDataSetChanged()
    }

    open inner class RestaurantHolder(view: View?) : RecyclerView.ViewHolder(view)

    inner class RestaurantItemHolder(override val containerView: View?) : RestaurantHolder(containerView), LayoutContainer {
        fun bind(model: RestaurantEntity) {
            rr_name.text = model.name
            rr_rate.rating = model.rate.rateValue as Float
            Picasso.get().load(model.restaurantPictures[0].url).into(rr_image)
            rr_duration_value.text = model.foodMeanTime.split(" ")[0];
            containerView?.tag = model.id
            containerView?.setOnClickListener {
                var restaurantId : Long = it.tag as Long
                val intent = Intent(it.context, RestaurantDetailsActivity::class.java)
                intent.putExtra("restaurant_id", restaurantId)
                it.context.startActivity(intent)
            }
        }
    }
}
