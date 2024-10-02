package com.dpdrijawabarat.app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// data class untuk menampung variable anggota
@Parcelize
data class Anggota(
    val urlImage: String = "",
    val nama: String = "",
    val jabatan: String = "",
    val desc: String = "",
    val ttl: String = "",
    val agama: String = "",
    val email: String = "",
    val status: String = "",
    val periode: String = "",
    val perolehanSuara: String = "",
    val type: String = "",
    val provinsi: String = "Jawa Barat"
): Parcelable
