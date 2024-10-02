package com.dpdrijawabarat.app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dpdrijawabarat.R
import com.dpdrijawabarat.app.adapter.AnggotaAdapter
import com.dpdrijawabarat.app.callback.FetchItems
import com.dpdrijawabarat.app.model.Anggota
import com.dpdrijawabarat.app.util.Constant
import com.dpdrijawabarat.app.util.Util
import com.dpdrijawabarat.databinding.ActivityAnggotaBinding

class AnggotaActivity : AppCompatActivity(), FetchItems {
    private lateinit var binding: ActivityAnggotaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnggotaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.appBarHome.appBarHome.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val drawerLayout = binding.drawerLayout
        val navView = binding.navView

        //navigasi samping
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, AuthActivity::class.java))
                    finish()
                }

                R.id.nav_gallery -> {
                    Util.openInstagram(this)

                }

                R.id.nav_slideshow -> {
                    Util.openTikTokProfile(this)
                }

                else -> return@setNavigationItemSelectedListener false
            }

            drawerLayout.closeDrawers()
            true
        }
        //menambahkan data yang ada di Anggota untuk di tampilkan di gridview
        val adapter = AnggotaAdapter(this, Constant.anggota, this)
        binding.appBarHome.gridView.adapter = adapter
        //menampilkan drawer saya menu hambuger di klik
        binding.appBarHome.appBarHome.btnHamburger.setOnClickListener {
            if (drawerLayout.isDrawerOpen(navView)) {
                drawerLayout.closeDrawer(navView)
            } else {
                drawerLayout.openDrawer(navView)
            }
        }
    }

    // callbak yang membawa parcelable untuk di kirim ke activity detail
    override fun onIntent(anggota: Anggota) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("anggota", anggota);
        startActivity(intent)
    }
}