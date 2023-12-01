package ru.madara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ru.madara.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var maxValue: Int = 100
    private var minValue: Int = 0

    private lateinit var inputMinValue : EditText
    private lateinit var inputMaxValue : EditText
    private lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputMinValue = binding.inputMinValue
        inputMaxValue = binding.inputMaxValue
        button = binding.startGame

        button.setOnClickListener {
            startGame()
        }
    }

    private fun startGame() {
        val intent = Intent(this, GameActivity::class.java)

        if (inputMaxValue.text.isNotEmpty() && inputMinValue.text.isNotEmpty()) {
            try {
                minValue = inputMinValue.text.toString().toInt()
                maxValue = inputMaxValue.text.toString().toInt()

                if (minValue >= maxValue) {
                    Toast.makeText(this, "Нижняя граница должна быть больше верхней", Toast.LENGTH_LONG).show()
                    return
                }

                intent.putExtra(GameActivity.ARG_MIN_VALUE, minValue)
                intent.putExtra(GameActivity.ARG_MAX_VALUE, maxValue)

                startActivity(intent)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Неправильный формат чисел", Toast.LENGTH_LONG).show()
            }
        }
    }
}