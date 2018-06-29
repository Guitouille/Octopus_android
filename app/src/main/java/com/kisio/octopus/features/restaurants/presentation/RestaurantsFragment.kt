package com.kisio.octopus.features.restaurants.presentation

import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.kisio.octopus.R
import com.kisio.octopus.core.exception.Failure
import com.kisio.octopus.core.extension.observe
import com.kisio.octopus.core.extension.failure
import com.kisio.octopus.core.extension.viewModel
import com.kisio.octopus.core.platform.BaseFragment
import com.kisio.octopus.features.restaurants.model.RestaurantEntity
import kotlinx.android.synthetic.main.fragment_restaurants.*
import javax.inject.Inject

class RestaurantsFragment : BaseFragment(), OnMapReadyCallback {

    @Inject
    lateinit var restaurantsAdapter: RestaurantsAdapter

    private var gMap : GoogleMap? = null
    private lateinit var restaurantsViewModel: RestaurantsViewModel
    private var locationManager: LocationManager? = null
    private var isMapViewReady : Boolean = false

    override fun layoutId(): Int = R.layout.fragment_restaurants

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        restaurantsViewModel = viewModel(viewModelFactory) {
            observe(restaurantsLiveData, ::renderConnectionSuccess)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        fh_mapview?.onResume()
    }

    override fun onPause() {
        super.onPause()
        fh_mapview?.onPause()
    }

    override fun onStart() {
        super.onStart()
        fh_mapview?.onStart()
    }

    override fun onStop() {
        super.onStop()
        fh_mapview?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        fh_mapview?.onDestroy()
    }
    private fun initializeView(savedInstanceState: Bundle?) {
        fh_restaurant_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fh_restaurant_list.adapter = restaurantsAdapter
        fh_mapview.onCreate(savedInstanceState)
        fh_mapview.getMapAsync(this)
    }

    private fun loadData() {
/*        emptyView.visible()
        departuresList.visible()*/
        showProgress()
        //connectionViewModel.authenticateUser()
    }

    private fun renderConnectionSuccess(restaurants: List<RestaurantEntity>?) {
        hideProgress()
        restaurantsAdapter.setData(restaurants ?: emptyList())
        gMap?.clear()
        restaurants?.forEach {
            var markerOption = MarkerOptions().position(LatLng(it.lat, it.lng))
            var marker : Marker? = gMap?.addMarker(markerOption)
            marker?.title = it.name
            marker?.tag = it
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadData)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        gMap = googleMap
        isMapViewReady = true
        gMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng( 48.846866, 2.377181), 14f)) //move camera to location
        gMap?.setMinZoomPreference(6.0f)
        gMap?.setMaxZoomPreference(14.0f)
        gMap?.uiSettings?.isZoomControlsEnabled = false
        gMap?.uiSettings?.isMyLocationButtonEnabled = false
        gMap?.uiSettings?.isCompassEnabled = false
        gMap?.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener {
            var restaurant = it.tag as RestaurantEntity

             true
        })

        restaurantsViewModel.getRestaurants()
    }
}