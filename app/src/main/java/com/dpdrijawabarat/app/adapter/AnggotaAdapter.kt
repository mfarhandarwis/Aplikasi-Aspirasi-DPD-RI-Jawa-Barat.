package com.dpdrijawabarat.app.adapter

import android.content.Context
import android.os.Build.VERSION_CODES.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dpdrijawabarat.R
import com.dpdrijawabarat.app.callback.FetchItems
import com.dpdrijawabarat.app.model.Anggota
import mfd.kp.dpdrijawabarat.R

class AnggotaAdapter(
    private val context: Context,
    private val anggotaList: List<Anggota>,
    private var listener: FetchItems
) :
    BaseAdapter() {
    //mendapatkan jumlah item di gridview
    override fun getCount(): Int {
        return anggotaList.size
    }

    //mendapatkan posisi item
    override fun getItem(position: Int): Any {
        return anggotaList[position]
    }

    //mendapatkan ID
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //menampilkan gambar ke Gridview
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_anggota, null)
        }

        //inisialisasi data dan komponen yang akan memuat data
        val anggota = anggotaList[position]
        val ivAnggota: ImageView = view!!.findViewById(R.id.ivAnggota)
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val type: TextView = view.findViewById(R.id.type)
        val provinsi: TextView = view.findViewById(R.id.provinsi)

        //memasukkan data agar bisa tampil di list
        Glide.with(context).load(anggota.urlImage).into(ivAnggota)
        tvNama.text = anggota.nama
        type.text = anggota.type
        provinsi.text = anggota.provinsi

        //ggar bisa di klik
        ivAnggota.setOnClickListener {
            listener.onIntent(anggota)
        }

        return view
    }
}