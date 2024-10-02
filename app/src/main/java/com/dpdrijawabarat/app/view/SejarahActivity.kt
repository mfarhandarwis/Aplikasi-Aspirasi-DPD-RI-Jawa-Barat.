package com.dpdrijawabarat.app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dpdrijawabarat.R
import com.dpdrijawabarat.databinding.ActivitySejarahBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util

class SejarahActivity : AppCompatActivity() {

    //membuat variable
    private var player1: SimpleExoPlayer? = null
    private var player2: SimpleExoPlayer? = null
    private lateinit var playerView1: PlayerView
    private lateinit var playerView2: PlayerView
    //mengatur url video yang ada di firebasee
    private val videoURL1 = "https://firebasestorage.googleapis.com/v0/b/dpdrijawabarat-4f669.appspot.com/o/VideoSejarah%2Fvideo1.mp4?alt=media&token=a3888684-43c2-4182-9c24-90e07b97901b"
    private val videoURL2 = "https://firebasestorage.googleapis.com/v0/b/dpdrijawabarat-4f669.appspot.com/o/VideoSejarah%2Fvideo2.mp4?alt=media&token=0cfe6edf-ba88-499c-924e-2b6f92c68120"

    private lateinit var binding: ActivitySejarahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySejarahBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //imisialisai view komponent yang akan di pakai
        playerView1 = binding.appBarHome.playerView
        playerView2 = binding.appBarHome.playerView2

        setSupportActionBar(binding.appBarHome.appBarHome.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //navigasi
        val drawerLayout = binding.drawerLayout
        val navView = binding.navView
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, AuthActivity::class.java))
                    finish()
                }
                R.id.nav_gallery -> {
                    com.dpdrijawabarat.app.util.Util.openInstagram(this)

                }

                R.id.nav_slideshow -> {
                    com.dpdrijawabarat.app.util.Util.openTikTokProfile(this)
                }
                else -> return@setNavigationItemSelectedListener false
            }

            drawerLayout.closeDrawers()
            true
        }
        binding.appBarHome.appBarHome.btnHamburger.setOnClickListener {
            if (drawerLayout.isDrawerOpen(navView)) {
                drawerLayout.closeDrawer(navView)
            } else {
                drawerLayout.openDrawer(navView)
            }
        }

    }

    //ketika di halaman tampil akan memutar video
    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initPlayer()
        }
    }

    //ketika kembali ke halaman akan memutar video
    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24 || player1 == null || player2 == null) {
            initPlayer()
        }
    }

    //ketika di halamana pause akan menghentikan video
    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayers()
        }
    }

    //ketika di halamana di destroy akan menghentikan video
    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayers()
        }
    }

    //untuk mangosongkan nilai plauer
    private fun releasePlayers() {
        player1?.release()
        player2?.release()
        player1 = null
        player2 = null
    }

    //memasukkan url video utuk di putar di player
    private fun initPlayer() {
        if (player1 == null) {
            player1 = SimpleExoPlayer.Builder(this).build()
            playerView1.player = player1
            player1!!.playWhenReady = true
            player1!!.setMediaSource(buildMediaSource(videoURL1))
            player1!!.prepare()
        }

        if (player2 == null) {
            player2 = SimpleExoPlayer.Builder(this).build()
            playerView2.player = player2
            player2!!.playWhenReady = true
            player2!!.setMediaSource(buildMediaSource(videoURL2))
            player2!!.prepare()
        }
    }

    // mendaptakan data mediastore
    private fun buildMediaSource(videoURL: String): MediaSource {
        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(videoURL))
    }
}
