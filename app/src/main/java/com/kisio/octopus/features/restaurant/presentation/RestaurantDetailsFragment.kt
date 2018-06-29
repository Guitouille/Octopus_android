package com.kisio.octopus.features.restaurant.presentation

import android.os.Bundle
import android.support.annotation.StringRes
import android.view.View
import com.kisio.octopus.R
import com.kisio.octopus.core.exception.Failure
import com.kisio.octopus.core.extension.failure
import com.kisio.octopus.core.extension.observe
import com.kisio.octopus.core.extension.viewModel
import com.kisio.octopus.core.platform.BaseFragment
import com.kisio.octopus.features.restaurants.model.PictureEntity
import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import kotlinx.android.synthetic.main.fragment_restaurant_details.*
import ss.com.bannerslider.viewholder.ImageSlideViewHolder
import ss.com.bannerslider.adapters.SliderAdapter




class RestaurantDetailsFragment : BaseFragment() {

    private lateinit var restaurantViewModel: RestaurantDetailsViewModel

    override fun layoutId(): Int = R.layout.fragment_restaurant_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        restaurantViewModel = viewModel(viewModelFactory) {
            observe(restaurantsLiveData, ::renderConnectionSuccess)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantViewModel.getRestaurant(arguments?.getLong("restaurant_id", 1L) as Long)
    }

    private fun loadData() {
    }

    private fun renderConnectionSuccess(restaurant: RestaurantEntity?) {

        val pictures = restaurant?.restaurantPictures
        frd_images_slider.setAdapter(MainSliderAdapter(pictures as List<PictureEntity>))

        frd_name.text = restaurant?.name
        frd_description.text = restaurant?.description
        frd_duration.text = String.format("Temps total pause déjeuner : %s", restaurant?.foodMeanTime)
        frd_address.text = restaurant?.address
        frd_phone_number.text = restaurant?.phoneNumber


    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        notifyWithAction(message, R.string.action_refresh, ::loadData)
    }

    open inner class MainSliderAdapter(var pictureList : List<PictureEntity>) : SliderAdapter() {

        override fun getItemCount(): Int {
            return pictureList.size
        }

        override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
            if (position < pictureList.size) {
                viewHolder.bindImageSlide(pictureList[position].url)
            }
        }
    }
}