package com.dpdrijawabarat.app.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dpdrijawabarat.R
import com.dpdrijawabarat.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inisilisasi komponent
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val web = binding.webView
        web.settings.javaScriptEnabled = true

        // memasukkan data url yang akan ditampilkan pada webview
        web.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSf3dLg9L1OCY-YN6I0d4VxqfxI5yNuzRa3AqPiSZxkXrofftg/viewform")

        //memuata halaman webview
        web.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                //jika url benar makan halaman akan di muat di webview
                view?.loadUrl(url!!)
                return true
            }
        }
    }
}