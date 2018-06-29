package com.kisio.octopus.features.restaurant.presentation

import android.os.Bundle
import com.kisio.octopus.core.platform.BaseActivity
import com.kisio.octopus.core.platform.BaseFragment

class RestaurantDetailsActivity : BaseActivity(){
    override fun fragment(): BaseFragment  {
        val fragment = RestaurantDetailsFragment()
        val args = Bundle()
        args.putLong("restaurant_id", intent.getLongExtra("restaurant_id", -1))
        fragment.arguments = args

        return fragment
    }
}