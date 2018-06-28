package com.kisio.octopus.features.restaurants.presentation

import com.kisio.octopus.core.platform.BaseActivity
import com.kisio.octopus.core.platform.BaseFragment

class RestaurantsActivity : BaseActivity(){
    override fun fragment(): BaseFragment = RestaurantsFragment()
}