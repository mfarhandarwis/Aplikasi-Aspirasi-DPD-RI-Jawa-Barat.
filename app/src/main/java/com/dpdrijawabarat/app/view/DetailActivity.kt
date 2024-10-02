package com.dpdrijawabarat.app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dpdrijawabarat.R
import com.dpdrijawabarat.app.model.Anggota
import com.dpdrijawabarat.app.util.Util
import com.dpdrijawabarat.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        val rootBinding = binding.appBarHome
        setContentView(view)
        //mendapatkan data parcelable
        val intent: Intent = intent
        val anggota : Anggota? = intent.getParcelableExtra("anggota")

        //menampilkan data komponen detail
        Glide.with(applicationContext)
            .asBitmap()
            .load(anggota?.urlImage)
            .into(rootBinding.imgAnggota)
        rootBinding.agama.text = anggota?.agama
        rootBinding.name.text = anggota?.nama
        rootBinding.ttl.text = anggota?.ttl
        rootBinding.email.text = anggota?.email
        rootBinding.jabatan.text = anggota?.jabatan
        rootBinding.periode.text = anggota?.periode
        rootBinding.perolehanSuara.text = anggota?.perolehanSuara
        rootBinding.status.text = anggota?.status
        rootBinding.jabatan.text = anggota?.jabatan
        rootBinding.desc.text = anggota?.desc

        // melakukan intent untuk membukan email
        binding.appBarHome.emailBtn.setOnClickListener {
            val intentEmail = Intent(Intent.ACTION_SEND)
            intentEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf(anggota?.email))
            intentEmail.putExtra(Intent.EXTRA_SUBJECT, "")
            intentEmail.putExtra(Intent.EXTRA_TEXT, "")
            intentEmail.type = "message/rfc822"
            startActivity(Intent.createChooser(intentEmail, "Choose an Email client :"))
        }

        // mengatur appbar dan toolbar
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

        //menampilkan drawer saya menu di klik
        binding.appBarHome.appBarHome.btnHamburger.setOnClickListener {
            if (drawerLayout.isDrawerOpen(navView)) {
                drawerLayout.closeDrawer(navView)
            } else {
                drawerLayout.openDrawer(navView)
            }
        }
    }
}