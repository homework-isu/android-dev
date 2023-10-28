package com.example.addnums

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.addnums.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClick(view: View) {
        val fVal = binding.firstNum.text.toString()
        val sVal = binding.secondNum.text.toString()

        if (fVal.isEmpty() || sVal.isEmpty()) {
            Toast.makeText(this, "Числа не введены", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val fNum = fVal.toFloat()
                val sNum = sVal.toFloat()
                val sum = fNum + sNum
                binding.result.text = sum.toString()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Числа введены неверно", Toast.LENGTH_SHORT).show()
            }
        }
    }
}