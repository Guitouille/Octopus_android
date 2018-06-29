package com.kisio.octopus.features.restaurants.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kisio.octopus.R
import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import kotlinx.android.extensions.LayoutContainer
import javax.inject.Inject
import kotlin.properties.Delegates

class RestaurantsAdapter @Inject constructor() : RecyclerView.Adapter<RestaurantsAdapter.RestaurantHolder>() {

    internal var restaurantList: MutableList<RestaurantEntity> by Delegates.observable(mutableListOf()) {
        _,_ , _ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHolder =
            RestaurantItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_departures, parent, false))


    override fun getItemCount(): Int {
        return restaurantList.size
    }

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setData(restaurants: List<RestaurantEntity>) {
        restaurantList.clear()
        restaurantList.addAll(restaurants)
    }

    open inner class RestaurantHolder(view: View?) : RecyclerView.ViewHolder(view)

    inner class RestaurantItemHolder(override val containerView: View?) : RestaurantHolder(containerView), LayoutContainer {
        fun bind(model: RestaurantEntity) {

        }
    }
}