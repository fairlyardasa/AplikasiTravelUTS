package com.example.aplikasitraveluts

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aplikasitraveluts.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding
    companion object{
        const val EXTRA_USERNAME = "username"
        const val EXTRA_EMAIL = "email"
        const val EXTRA_PASSWORD = "password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val context = binding.root.context
        with(binding){
                edtBday.setOnClickListener(){
                    val calendar = Calendar.getInstance()
                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH)
                    val day = calendar.get(Calendar.DAY_OF_MONTH)
                    val datePickerDialog = DatePickerDialog(context, { _, y, m, d ->
                        edtBday.setText("$d/${m+1}/$y")
                    }, year, month, day)
                    datePickerDialog.show()
                }

                btnRegister.setOnClickListener(){
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val date = sdf.parse(edtBday.text.toString())
                    val calendar = Calendar.getInstance()
                    calendar.time = date
                    val year = calendar.get(Calendar.YEAR)

                    val uname = edtUsername.text.toString()
                    val email = edtEmail.text.toString()
                    val password = edtPassword.text.toString()




                    if (2023-year > 15 && edtBday.text != null) {
                        Toast.makeText(this@MainActivity, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show()
                        val intentToLoginActivity =
                            Intent(this@MainActivity, LoginActivity::class.java).apply {

                                putExtra(EXTRA_USERNAME, uname)
                                putExtra(EXTRA_PASSWORD, password)
                                putExtra(EXTRA_EMAIL, email)


                            }

                        startActivity(intentToLoginActivity)

                    } else Toast.makeText(this@MainActivity, "Pendaftaran gagal", Toast.LENGTH_SHORT).show()

                }
        }
    }

}