package com.dat.android.gamebit.presentation

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dat.android.gamebit.R
import com.dat.android.gamebit.presentation.black.BlackActivity
import com.dat.android.gamebit.presentation.menu.MenuActivity
import com.dat.android.gamebit.utils.Analytics
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    lateinit var vm: SplashVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateUI()
        Analytics.open()

        vm = ViewModelProviders.of(this).get(SplashVM::class.java)
        vm.getStatusLD().observe(this, Observer {
            when (it) {
                SplashVM.BLACK -> openScreen()
                SplashVM.WHITE -> openMenu()
            }
        })

    }

    private fun updateUI() {
        var span = SpannableString(getString(R.string.splash_title))
        span.setSpan(ForegroundColorSpan(resources.getColor(R.color.end_splash_title)), 4, span.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvTitle.text = span
        startAnim()
    }

    private fun startAnim() {
        var anim = ValueAnimator.ofFloat(0f, 30_000f)
        anim.duration = 60_000L
        anim.addUpdateListener {
            iv_roulette_splash_giv.rotation = it.animatedValue.toString().toFloat()
        }
        anim.start()
    }

    private fun openScreen(){
        Analytics.openScreen()
        intent = Intent(this@SplashActivity, BlackActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun openMenu(){
        Analytics.openMenu()
        intent = Intent(this@SplashActivity, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}

