package com.example.aplikasitraveluts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aplikasitraveluts.MainActivity.Companion.EXTRA_EMAIL
import com.example.aplikasitraveluts.MainActivity.Companion.EXTRA_PASSWORD
import com.example.aplikasitraveluts.MainActivity.Companion.EXTRA_USERNAME
import com.example.aplikasitraveluts.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uname = intent.getStringExtra(EXTRA_USERNAME)
        val password = intent.getStringExtra(EXTRA_PASSWORD)
        val email = intent.getStringExtra(EXTRA_EMAIL)

        with(binding){
            btnLogin.setOnClickListener(){
                if (edtUsername.text.toString() == uname && edtPassword.text.toString() == password) {
                    val intentToDashboardActivity =
                        Intent(this@LoginActivity, DashboardActivity::class.java).apply {
                            putExtra(EXTRA_USERNAME, uname)

                        }

                    startActivity(intentToDashboardActivity)

                } else Toast.makeText(this@LoginActivity, "Login Gagal!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}