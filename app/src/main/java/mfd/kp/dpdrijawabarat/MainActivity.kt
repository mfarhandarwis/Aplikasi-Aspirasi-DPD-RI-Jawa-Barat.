package mfd.kp.dpdrijawabarat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screem)

        Handler().postDelayed({
            startActivity(Intent(this,LogiinLogOut::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}