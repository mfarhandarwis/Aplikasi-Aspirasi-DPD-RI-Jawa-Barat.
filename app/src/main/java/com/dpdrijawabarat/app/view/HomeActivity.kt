package com.dpdrijawabarat.app.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dpdrijawabarat.R
import com.dpdrijawabarat.app.util.Util
import com.dpdrijawabarat.app.util.Util.openInstagram
import com.dpdrijawabarat.app.util.Util.openTikTokProfile
import com.dpdrijawabarat.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //mengatur appbar dan toolbar
        setSupportActionBar(binding.appBarHome.appBarHome.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //navigasi drawer
        val drawerLayout = binding.drawerLayout
        val navView = binding.navView
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, AuthActivity::class.java))
                    finish()
                }

                R.id.nav_gallery -> {
                    openInstagram(this)

                }

                R.id.nav_slideshow -> {
                    openTikTokProfile(this)
                }

                else -> return@setNavigationItemSelectedListener false
            }

            drawerLayout.closeDrawers()
            true
        }
        //ke halaman aspirasi
        binding.appBarHome.aspirasi.setOnClickListener {
            startActivity(Intent(applicationContext, WebviewActivity::class.java))
        }
        //ke halaman anggota
        binding.appBarHome.anggota.setOnClickListener {
            startActivity(Intent(applicationContext, AnggotaActivity::class.java))
        }
        //ke halaman tugas
        binding.appBarHome.tugas.setOnClickListener {
            startActivity(Intent(applicationContext, TugasActivity::class.java))
        }
        //ke halaman sejarah
        binding.appBarHome.sejarah.setOnClickListener {
            startActivity(Intent(applicationContext, SejarahActivity::class.java))
        }
        //ke halaman profile
        binding.appBarHome.profil.setOnClickListener {
            startActivity(Intent(applicationContext, ProfileActivity::class.java))
        }
        //menampikan appdrawer jika di klik mennu
        binding.appBarHome.appBarHome.btnHamburger.setOnClickListener {
            if (drawerLayout.isDrawerOpen(navView)) {
                drawerLayout.closeDrawer(navView)
            } else {
                drawerLayout.openDrawer(navView)
            }
        }
    }


}


