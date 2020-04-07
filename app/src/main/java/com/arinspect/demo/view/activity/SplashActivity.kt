package com.arinspect.demo.view.activity

import android.os.Bundle
import com.arinspect.demo.R
import com.arinspect.demo.databinding.ActivitySplashBinding
import com.arinspect.demo.view.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_splash)
    }
}
