package com.example.aplikasitraveluts

import android.R
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ToggleButton
import com.example.aplikasitraveluts.databinding.ActivityOrderBinding
import java.util.Calendar


class OrderActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TUJUAN = "Extra Address"
        const val EXTRA_ASAL = "Asal"
        const val EXTRA_CLASS = "Class"
        const val EXTRA_SCHEDULE = "Schedule"
        const val EXTRA_PRICE = "Price"
        const val EXTRA_SERVICES = "Services"
    }

    private lateinit var binding : ActivityOrderBinding

    var tujuanPrices = 0
    var asalPrices = 0
    var clasPrices = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){

            edtSchedule.setOnClickListener(){
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(this@OrderActivity, { _, y, m, d ->
                    edtSchedule.setText("$d/${m+1}/$y")
                }, year, month, day)
                datePickerDialog.show()
            }

            val asal = arrayOf(
                arrayOf("Tawang","20000"),
                arrayOf("Poncol","25000"),
                arrayOf("Tegal","30000"),
                arrayOf("Pekalongan","35000"),
                arrayOf("Ambarawa","40000")
            )

            val tujuan = arrayOf(
                arrayOf("Ampera","20000"),
                arrayOf("Andir","25000"),
                arrayOf("Babakam","30000"),
                arrayOf("Batutulis","35000"),
                arrayOf("Bogor","40000")
            )

            val classes = arrayOf(
                arrayOf("Economic","50000"),
                arrayOf("Business","100000")
            )

            val asalList = getDaerahList(asal)
            val tujuanList = getDaerahList(tujuan)
            val classList = getDaerahList(classes)

            val adapterAsal = ArrayAdapter<String>(
                this@OrderActivity, R.layout.simple_spinner_item,asalList
            )

            val adapterTujuan = ArrayAdapter<String>(
                this@OrderActivity, R.layout.simple_spinner_item, tujuanList
            )

            val adapterClasses = ArrayAdapter<String>(
                this@OrderActivity, R.layout.simple_spinner_item, classList
            )


            spinnerStasiunAsal.adapter= adapterAsal
            spinnerStasiunTujuan.adapter = adapterTujuan
            spinnerClass.adapter = adapterClasses


            spinnerStasiunTujuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selectedItem = spinnerStasiunTujuan.getItemAtPosition(p2).toString()
                    val price = getSelectedPrice(tujuan, selectedItem)
                    tujuanPrices = price?.toInt() ?: 0 // Use 0 as a default value if price is null
                    val prices = tujuanPrices + asalPrices + clasPrices
                    txtPrices.text = "Rp $prices"

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

            spinnerStasiunAsal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selectedItem = spinnerStasiunAsal.getItemAtPosition(p2).toString()
                    val price = getSelectedPrice(asal, selectedItem)
                    asalPrices = price?.toInt() ?: 0 // Use 0 as a default value if price is null
                    val prices = tujuanPrices + asalPrices + clasPrices
                    txtPrices.text = "Rp $prices"

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

            spinnerClass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selectedItem = spinnerClass.getItemAtPosition(p2).toString()
                    val price = getSelectedPrice(classes, selectedItem)
                    clasPrices = price?.toInt() ?: 0 // Use 0 as a default value if price is null
                    val prices = tujuanPrices + asalPrices + clasPrices
                    txtPrices.text = "Rp $prices"

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }


            togglbtnBagasi.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val prices = tujuanPrices + asalPrices + clasPrices + 50000
                    txtPrices.text = "Rp " + prices.toString()
                } else {
                    val prices = tujuanPrices + asalPrices + clasPrices
                    txtPrices.text = "Rp " + prices.toString()
                }
            }
            togglbtnHeadrest.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val prices = tujuanPrices + asalPrices + clasPrices + 25000
                    txtPrices.text = "Rp " + prices.toString()
                } else {
                    val prices = tujuanPrices + asalPrices + clasPrices
                    txtPrices.text = "Rp " + prices.toString()
                }
            }
            togglbtnKaraoke.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val prices = tujuanPrices + asalPrices + clasPrices + 50000
                    txtPrices.text = "Rp " + prices.toString()
                } else {
                    val prices = tujuanPrices + asalPrices + clasPrices
                    txtPrices.text = "Rp " + prices.toString()
                }
            }
            togglbtnMakan.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val prices = tujuanPrices + asalPrices + clasPrices + 25000
                    txtPrices.text = "Rp " + prices.toString()
                } else {
                    val prices = tujuanPrices + asalPrices + clasPrices
                    txtPrices.text = "Rp " + prices.toString()
                }
            }
            togglbtnKursi.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val prices = tujuanPrices + asalPrices + clasPrices + 10000
                    txtPrices.text = "Rp " + prices.toString()
                } else {
                    val prices = tujuanPrices + asalPrices + clasPrices
                    txtPrices.text = "Rp " + prices.toString()
                }
            }
            togglbtnMinibar.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val prices = tujuanPrices + asalPrices + clasPrices + 30000
                    txtPrices.text = "Rp " + prices.toString()
                } else {
                    val prices = tujuanPrices + asalPrices + clasPrices
                    txtPrices.text = "Rp " + prices.toString()
                }
            }
            togglbtnTV.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val prices = tujuanPrices + asalPrices + clasPrices + 60000
                    txtPrices.text = "Rp " + prices.toString()
                } else {
                    val prices = tujuanPrices + asalPrices + clasPrices
                    txtPrices.text = "Rp " + prices.toString()
                }
            }

            togglbtnToilet.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val prices = tujuanPrices + asalPrices + clasPrices + 60000
                    txtPrices.text = "Rp " + prices.toString()
                } else {
                    val prices = tujuanPrices + asalPrices + clasPrices
                    txtPrices.text = "Rp " + prices.toString()
                }
            }

            btnSubmit.setOnClickListener(){
                val activatedToggleButtons = mutableListOf<String>()

                if (togglbtnBagasi.isChecked) {
                    activatedToggleButtons.add("Bagasi")
                }
                if (togglbtnHeadrest.isChecked) {
                    activatedToggleButtons.add("Headrest")
                }
                if (togglbtnKaraoke.isChecked) {
                    activatedToggleButtons.add("Karaoke")
                }
                if (togglbtnMakan.isChecked) {
                    activatedToggleButtons.add("Makan")
                }
                if (togglbtnKursi.isChecked) {
                    activatedToggleButtons.add("Kursi")
                }
                if (togglbtnMinibar.isChecked) {
                    activatedToggleButtons.add("Minibar")
                }
                if (togglbtnTV.isChecked) {
                    activatedToggleButtons.add("TV")
                }
                if (togglbtnToilet.isChecked) {
                    activatedToggleButtons.add("Toilet")
                }
                val selectedItems = activatedToggleButtons.joinToString(", ")
                val prices = tujuanPrices + asalPrices + clasPrices
                val totalPriceText = "Rp $prices"

                val selectedAsal = spinnerStasiunAsal.selectedItem.toString()
                val selectedTujuan = spinnerStasiunTujuan.selectedItem.toString()
                val selectedClass = spinnerClass.selectedItem.toString()

                val selectedDate = edtSchedule.text.toString()

                val intent = Intent()
                intent.putExtra(EXTRA_TUJUAN, selectedTujuan)
                intent.putExtra(EXTRA_ASAL, selectedAsal)
                intent.putExtra(EXTRA_CLASS, selectedClass)
                intent.putExtra(EXTRA_SCHEDULE, selectedDate)
                intent.putExtra(EXTRA_PRICE, prices)
                intent.putExtra(EXTRA_SERVICES, selectedItems)

                setResult(Activity.RESULT_OK,intent)
                finish()

            }

        }

    }

    fun getDaerahList(tujuan: Array<Array<String>>): List<String> {
        val tujuanList = mutableListOf<String>()
        for (i in tujuan.indices) {
            tujuanList.add(tujuan[i][0] as String)
        }
        return tujuanList
    }

    fun getSelectedPrice(array: Array<Array<String>>, target: String): String? {
        for (i in array.indices) {
            if (array[i][0] == target) {
                return array[i][1]
            }
        }
        return null
    }


}