package com.example.aplikasitraveluts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.aplikasitraveluts.OrderActivity.Companion.EXTRA_ASAL
import com.example.aplikasitraveluts.OrderActivity.Companion.EXTRA_CLASS
import com.example.aplikasitraveluts.OrderActivity.Companion.EXTRA_PRICE
import com.example.aplikasitraveluts.OrderActivity.Companion.EXTRA_SCHEDULE
import com.example.aplikasitraveluts.OrderActivity.Companion.EXTRA_SERVICES
import com.example.aplikasitraveluts.OrderActivity.Companion.EXTRA_TUJUAN
import com.example.aplikasitraveluts.databinding.ActivityDashboardBinding
import java.io.Serializable

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDashboardBinding
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->

            if (result.resultCode == RESULT_OK){
                val data = result.data
                val selectedTujuan = data?.getStringExtra(EXTRA_TUJUAN)
                val selectedAsal = data?.getStringExtra(EXTRA_ASAL)
                val selectedClass = data?.getStringExtra(EXTRA_CLASS)
                val selectedDate = data?.getStringExtra(EXTRA_SCHEDULE)
                val prices = data?.getIntExtra(EXTRA_PRICE, 0) // Provide a default value (0 in this case)
                val selectedItems = data?.getStringExtra(EXTRA_SERVICES)

                binding.cardDestination.text = "$selectedAsal - $selectedTujuan"
                binding.cardClass.text = "$selectedClass"
                binding.cardSchedule.text = "$selectedDate"
                binding.cardPrice.text = "$prices"
                binding.cardPaket.text = "$selectedItems"



            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uname = intent.getStringExtra(MainActivity.EXTRA_USERNAME)

        with(binding){

            txtUsername.text = uname

            val dates = arrayOf(
                arrayOf(
                    "12/10/2023", arrayOf(
                        "Cikini", "Gondangdia", "Business Class", "12-10-2023", "Rp 50.000"
                    )
                ),
                arrayOf(
                    "13/10/2023", arrayOf(
                        "Cikini", "Mangga Dua", "Economic Class", "13-10-2023", "Rp 60.000"
                    )
                ),
            )

            buttonPesan.setOnClickListener(){
                val intent = Intent(this@DashboardActivity, OrderActivity::class.java)
                launcher.launch(intent)
            }

            datepicker.init(
                datepicker.year,
                datepicker.month,
                datepicker.dayOfMonth){
                    _,year,month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month +1}/$year"
                val result = searchByDate(dates, selectedDate)
                if (result != null) {
                    Toast.makeText(this@DashboardActivity, "Sudah ada rencana pada tanggal tersebut", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@DashboardActivity, "Tidak ada rencana perjalanan pada tanggal tersebut", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun searchByDate(dates: Array<Array<Serializable>>, date: String): Array<Any>? {
        for (i in dates.indices) {
            if (dates[i][0] == date) {
                return dates[i][1] as Array<Any>
            }
        }
        return null
    }

}