package com.dpdrijawabarat.app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import com.dpdrijawabarat.R
import com.dpdrijawabarat.app.util.Util
import com.dpdrijawabarat.databinding.ActivityTugasBinding

class TugasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTugasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTugasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val tvProdDesc = binding.appBarHome.tvProdDesc

        setSupportActionBar(binding.appBarHome.appBarHome.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

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

        // mengisi nilai teks
        val longDescription = "Pengajuan Usul Rancangan Undang-Undang.\n" +
                "Pembahasan Rancangan Undang-Undang.\n" +
                "Pertimbangan Atas Rancangan Undang-Undang dan Pemilihan Anggota BPK.\n" +
                "Pengawasan atas Pelaksanaan Undang-Undang."

        val arr = longDescription.split("\n").toTypedArray()

        val bulletGap = dp(10).toInt()

        //menambahkan bullet pada teks
        val ssb = SpannableStringBuilder()
        for (i in arr.indices) {
            val line = arr[i]
            val ss = SpannableString(line)
            ss.setSpan(BulletSpan(bulletGap), 0, line.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            ssb.append(ss)

            if (i + 1 < arr.size)
                ssb.append("\n")
        }
        //menerapkan ke dalam textviw
        tvProdDesc.text = ssb

        // menampilkan drawer jika di klik menu
        binding.appBarHome.appBarHome.btnHamburger.setOnClickListener {
            if (drawerLayout.isDrawerOpen(navView)) {
                drawerLayout.closeDrawer(navView)
            } else {
                drawerLayout.openDrawer(navView)
            }
        }
    }

    // fungsi untuk mendapatakn sp dari integer ke float
    private fun dp(dp: Int): Float {
        return resources.displayMetrics.density * dp
    }
}