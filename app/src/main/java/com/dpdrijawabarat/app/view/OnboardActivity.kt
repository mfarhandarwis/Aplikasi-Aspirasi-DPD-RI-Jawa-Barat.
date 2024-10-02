package com.dpdrijawabarat.app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dpdrijawabarat.R

class OnboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)
        //berpindah ke halaman auth
        startActivity(Intent(this, AuthActivity::class.java))
    }
}