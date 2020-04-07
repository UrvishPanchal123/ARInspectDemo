package com.arinspect.demo.view.activity

import android.os.Bundle
import com.arinspect.demo.R
import com.arinspect.demo.databinding.ActivityMainBinding
import com.arinspect.demo.view.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_main)
    }
}
