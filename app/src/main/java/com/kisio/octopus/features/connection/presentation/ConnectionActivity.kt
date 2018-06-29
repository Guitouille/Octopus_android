package com.kisio.octopus.features.connection.presentation

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import com.kisio.octopus.R
import com.kisio.octopus.core.platform.BaseActivity
import com.kisio.octopus.core.platform.BaseFragment

class ConnectionActivity : BaseActivity(){
    override fun fragment(): BaseFragment = ConnectionFragment()
}