package com.dpdrijawabarat.app.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.dpdrijawabarat.R
import com.dpdrijawabarat.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible = false
    private lateinit var auth: FirebaseAuth
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        val view = binding.root
        setContentView(view)
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        // utnuk button login
        binding.btnMasuk.setOnClickListener {
            //mengambil nilai dari edittext
            val email = binding.email.text.toString().trim()
            val password = binding.edPassword.text.toString().trim()

            // validasi jika data kosong
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // mengecek data yang ada di firebase auth
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    //pengecekan data jika berhasil maka ke halaman home
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                        //jika gagal
                    } else {
                        Toast.makeText(this, "Login gagal: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
        }
    }

        //membaca setiap email yang di masukkan
        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches()
                updateEmailIcon(isValidEmail,  binding.email)
            }
        })

        //agar bisa menampilkan dan menyembunyikan password
        binding.edPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = binding.edPassword.compoundDrawablesRelative[2]
                if (drawableEnd != null && event.rawX >= (binding.edPassword.right - drawableEnd.bounds.width())) {
                    togglePasswordVisibility(binding.edPassword)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
    }

    //memunculkan checklist email jika input email sudah benar
    private fun updateEmailIcon(isValid: Boolean, editText: EditText) {
        val icon = if (isValid) R.drawable.round_check_circle_outline_24 else 0
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, icon, 0)
    }

    //mengganti icon mata jika melihat nilai password
    private fun togglePasswordVisibility(passwordEditText: EditText) {
        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            passwordEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0)
        } else {
            passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_24, 0)
        }
        passwordEditText.setSelection(passwordEditText.text?.length ?: 0)
    }
}