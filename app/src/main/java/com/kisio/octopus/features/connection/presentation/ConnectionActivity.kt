package com.kisio.octopus.features.connection.presentation

import com.kisio.octopus.core.platform.BaseActivity
import com.kisio.octopus.core.platform.BaseFragment

class ConnectionActivity : BaseActivity(){
    override fun fragment(): BaseFragment = ConnectionFragment()
}