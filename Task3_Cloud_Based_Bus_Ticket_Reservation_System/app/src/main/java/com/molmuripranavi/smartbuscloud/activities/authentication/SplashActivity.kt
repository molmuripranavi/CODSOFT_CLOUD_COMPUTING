package com.molmuripranavi.smartbuscloud.activities.authentication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fade = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        binding.logoContainer.startAnimation(fade)
        binding.loadingLayout.startAnimation(fade)

        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(
                Intent(
                    this,
                    RoleSelectionActivity::class.java
                )
            )

            finish()

        }, 2500)

    }
}