package com.kisio.octopus.features.create.presentation

import com.kisio.octopus.core.platform.BaseActivity
import com.kisio.octopus.core.platform.BaseFragment

class CreateActivity : BaseActivity(){
    override fun fragment(): BaseFragment = CreateFragment()
}