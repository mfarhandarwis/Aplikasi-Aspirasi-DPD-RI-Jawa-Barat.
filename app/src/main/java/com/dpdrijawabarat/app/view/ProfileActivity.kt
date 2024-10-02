package com.dpdrijawabarat.app.view

import android.animation.LayoutTransition
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.view.View
import com.dpdrijawabarat.R
import com.dpdrijawabarat.app.util.Util
import com.dpdrijawabarat.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        //mengatur appbar dan toolbar
        setSupportActionBar(binding.appBarHome.appBarHome.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setContentView(view)
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

        // menambahkan data pada texview
        binding.appBarHome.detailText2.text = "Hak dan Kewajiban Anggota DPD RI Sesuai dengan ketentuan Pasal 49 dan 50 Undang-Undang Nomor 22 Tahun 2003 tentang Susunan dan Kedudukan MPR, DPR, DPD, dan DPRD bahwa Anggota DPD mempunyai hak dan kewajiban sebagai berikut:\n\n" +
                "Hak\n\n" +
                "Menyampaikan usul dan pendapat .Memilih dan dipilih .Membela diri .Imunitas .Protokoler dan .Keuangan dan administratif.\n\n" +
                "Kewajiban\n\n" +
                "Mengamalkan Pancasila .Melaksanakan Undang-Undang Dasar Negara Republik Indonesia Tahun 1945 dan menaati segala peraturan perundang-undangan .Melaksanakan kehidupan demokrasi dalam penyelenggaraan pemerintahan .Mempertahankan dan memelihara kerukunan nasional dan keutuhan negara kesatuan Republik Indonesia .Memperhatikan upaya peningkatan kesejahteraan rakyat .Menyerap, menghimpun, menampung dan menindaklanjuti aspirasi masyarakat dan daerah .Mendahulukan kepentingan negara di atas kepentingan pribadi, kelompok, dan golongan .Memberikan pertanggungjawaban secara moral dan politis kepada pemilih dan daerah pemilihannya .Menaati kode etik dan Peraturan Tata Tertib DPD dan .Menjaga etika dan norma adat daerah yang diwakilinya.\n\n" +
                "Berkenaan dengan kewajiban tersebut, hal itu mempertegas fungsi politik legislatif Anggota DPD RI yang meliputi representasi, legislasi dan pengawasan yang dicirikan oleh sifat kekuatan mandatnya dari rakyat pemilih yaitu sifat “otoritatif” atau mandat rakyat kepada Anggota; di samping itu ciri sifat ikatan atau “binding” yaitu ciri melekatnya pemikiran dan langkah kerja Anggota DPD RI yang semata-mata didasarkan pada kepentingan dan keberpihakan pada rakyat daerah."
        binding.appBarHome.detailText.text = "VISI DPD-RI\n\n" +
                "Konsensus politik bangsa Indonesia melalui reformasi 1998 telah menghasilkan perubahan struktur ketatanegaraan Indonesia yang dituangkan dalam konstitusi. Perubahan tersebut antara lain menghadirkan Dewan Perwakilan Daerah Republik Indonesia (DPD RI) sebagai lembaga perwakilan selain Dewan Perwakilan Rakyat (DPR RI)\n\n" +
                "Lembaga DPD RI dibentuk melalui Perubahan Ketiga UUD 1945 tahun 2001 dalam rangka penguatan kelembagaan dari semula hanya setingkat Fraksi Utusan Daerah di MPR RI untuk mengatasi masalah hubungan pusat-daerah dan memperkuat ikatan daerah-daerah dalam NKRI serta membangun mekanisme check and balances antar cabang kekuasaan negara dan dalam cabang kekuasaan legislatif itu sendiri.\n\n" +
                "Berdasarkan hal tersebut maka visi DPD RI adalah sebagai berikut :\n\n" +
                "Menjadikan Dewan Perwakilan Daerah Republik Indonesia sebagai lembaga perwakilan yang mampu secara optimal dan akuntabel memperjuangkan aspirasi daerah untuk mewujudkan tujuan nasional demi kepentingan bangsa dan negara kesatuan Republik Indonesia\n\n" +
                "MISI DPD-RI\n\n" +
                "Berdasarkan visi tersebut, rumusan misi DPD RI disepakati sebagai berikut:\n\n" +
                "1. Memperkuat kewenangan DPD RI melalui amandemen UUD 1945;\n\n" +
                "2. Mengoptimalkan pelaksanaan fungsi legislasi, pengawasan dan penganggaran sesuai kewenangan yang ditetapkan oleh UUD 1945 dan Undang-Undang;\n\n" +
                "3. Memperkuat kapasitas pelaksanaan fungsi representasi yang mencakup penampungan dan penindaklanjutan aspirasi daerah dan pengaduan masyarakat serta peningkatan pemahaman masyarakat tentang kelembagaan DPD RI dalam rangka akuntabilitas publik;\n\n" +
                "4. Meningkatkan hubungan dan kerjasama dengan lembaga-lembaga negara/pemerintah dan non pemerintah di dalam negeri dan lembaga perwakilan negara-negara sahabat termasuk masyarakat parlemen internasional;\n\n" +
                "5. Meningkatkan kinerja dan kapasitas kelembagaan baik yang menyangkut tampilan perorangan para anggota DPD RI maupun pelaksanaan fungsi kesekretariatan jenderal termasuk tunjangan fungsional/keahlian."

        //mengatur transisi expanded card
        binding.appBarHome.layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.appBarHome.layout2.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        //expande cardview jika di klik
        binding.appBarHome.card.setOnClickListener {
            val visibility =
                if (binding.appBarHome.detailText.visibility == View.GONE) View.VISIBLE else View.GONE
            binding.appBarHome.detailText.visibility = visibility
        }

        //expande cardview jika di klik
        binding.appBarHome.card2.setOnClickListener {
            val visibility =
                if (binding.appBarHome.detailText2.visibility == View.GONE) View.VISIBLE else View.GONE
            binding.appBarHome.detailText2.visibility = visibility
        }

        //menampilkan navigation drawer jika menu di klik
        binding.appBarHome.appBarHome.btnHamburger.setOnClickListener {
            if (drawerLayout.isDrawerOpen(navView)) {
                drawerLayout.closeDrawer(navView)
            } else {
                drawerLayout.openDrawer(navView)
            }
        }
    }

}