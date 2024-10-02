package com.dpdrijawabarat.app.callback

import com.dpdrijawabarat.app.model.Anggota

//interface untuk mendapatkan callback data
interface FetchItems {
    fun onIntent(anggota : Anggota)
}