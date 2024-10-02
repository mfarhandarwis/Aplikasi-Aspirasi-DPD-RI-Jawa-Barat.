package com.dpdrijawabarat.app.util

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

import android.content.Context


object Util {
    //untuk membuka instagram
    @JvmStatic
    fun openInstagram(context: Context) {
        val packageName = "com.instagram.android"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.instagram.com/bulansutena/")
        val packageManager = context.packageManager
        if (intent.resolveActivity(packageManager) != null) {
            intent.setPackage(packageName)
            context.startActivity(intent)
        } else {
            intent.data = Uri.parse("https://www.instagram.com/bulansutena/")
            context.startActivity(intent)
            Toast.makeText(context, "Instagram app not installed.", Toast.LENGTH_SHORT).show()
        }
    }

    // untuk membuka tiktok
    @JvmStatic
    fun openTikTokProfile(context: Context) {
        val packageName = "com.zhiliaoapp.musically"
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)

        if (intent != null) {
            val profileUri = Uri.parse("https://www.tiktok.com/@bulansutena")
            val profileIntent = Intent(Intent.ACTION_VIEW, profileUri)
            context.startActivity(profileIntent)
        } else {
            val tiktokUri = Uri.parse("https://www.tiktok.com/@bulansutena")
            val websiteIntent = Intent(Intent.ACTION_VIEW, tiktokUri)
            context.startActivity(websiteIntent)

            Toast.makeText(context, "TikTok app not installed.", Toast.LENGTH_SHORT).show()
        }
    }
}
