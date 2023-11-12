package com.example.flighttickets

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.flighttickets.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerWhere: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        spinnerFrom = binding.cityFrom
        spinnerWhere = binding.cityWhere

        setSpinners()
        setDatePicker(binding.dateDeparture)
        setDatePicker(binding.dateArrival)
        setButton()
    }


    private fun setSpinners() {
        val cities = resources.getStringArray(R.array.cities)
        val adapter =
            ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_spinner_item, cities)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerFrom.adapter = adapter
        spinnerFrom.setSelection(0, false)
        spinnerWhere.adapter = adapter
        spinnerWhere.setSelection(0, false)
    }

    private fun setDatePicker(widget: TextView) {
        widget.setOnClickListener {
            DatePickerDialog(this).apply {
                setOnDateSetListener {_, year, month, day ->
                    val str = "$day-${month + 1}-$year"
                    widget.text = str
                }
                show()
            }
        }
    }

    private fun setButton() {
        binding.find.setOnClickListener {
            Toast.makeText(this, "Ищем подходящие варианты", Toast.LENGTH_LONG).show()
        }
    }

}