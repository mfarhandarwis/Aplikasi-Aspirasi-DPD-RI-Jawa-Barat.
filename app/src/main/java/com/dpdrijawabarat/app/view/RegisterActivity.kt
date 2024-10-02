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
import com.dpdrijawabarat.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    //membuat variabel
    private lateinit var binding: ActivityRegisterBinding
    private var isPasswordVisible = false
    private lateinit var auth: FirebaseAuth
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inisialisai variable
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        auth = FirebaseAuth.getInstance()
        setContentView(view)
        //mengatur agar fullscreen
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches()
                updateEmailIcon(isValidEmail,  binding.email)
            }
        })

        //untuk button register
        binding.btnDaftar.setOnClickListener {
            //mengambil value dari edittext
            val email = binding.email.text.toString().trim()
            val password = binding.edPassword.text.toString().trim()

            //mengecek jika edittext tidak ada isinya
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //menambahkan data ke firebase auth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {
                        Toast.makeText(this, "Registrasi gagal: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

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

    private fun updateEmailIcon(isValid: Boolean, editText: EditText) {
        val icon = if (isValid) R.drawable.round_check_circle_outline_24 else 0
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, icon, 0)
    }

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