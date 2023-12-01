package ru.madara

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import ru.madara.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {

    companion object {
        const val ARG_MIN_VALUE = "MIN_VALUE"
        const val ARG_MAX_VALUE = "MAX_VALUE"
    }

    private var minValue: Int = 0
    private var maxValue: Int = 0
    private var number: Int = 0
    private var steps: Int = 0

    private lateinit var numberTextView: TextView

    private lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            minValue = extras.getInt(ARG_MIN_VALUE)
            maxValue = extras.getInt(ARG_MAX_VALUE)
        }

        val lowerButton = binding.lower
        val isOkButton = binding.isOk
        val biggerButton = binding.bigger
        val againButton = binding.again

        numberTextView = binding.number

        lowerButton.setOnClickListener { lowerHandler() }
        isOkButton.setOnClickListener { isOkHandler() }
        biggerButton.setOnClickListener { biggerHandler() }
        againButton.setOnClickListener { againButton() }

        updateNumber()
    }

    private fun updateNumber() {
        number = (maxValue + minValue) / 2
        numberTextView.text = number.toString()
    }
    @SuppressLint("SetTextI18n")
    private fun isOkHandler() {
        binding.stepsToWin.text = "Шагов для подебы: $steps"
        endGame()
    }

    @SuppressLint("SetTextI18n")
    private fun isBadEndHandler() {
        endGame()
        binding.stepsToWin.text = "Кажется, вы обманули машину :("
    }
    private fun biggerHandler() {
        if (number + 1 >= maxValue) {
            isBadEndHandler()
        }

        if (minValue < maxValue) {
            minValue = (maxValue + minValue) / 2
            steps++
            updateNumber()
        } else {
            isBadEndHandler()
        }
    }
    private fun lowerHandler() {
        if (number + 1 <= minValue) {
            isBadEndHandler()
        }

        if (minValue < maxValue) {
            maxValue = (maxValue + minValue) / 2
            steps++
            updateNumber()
        } else {
            isBadEndHandler()
        }
    }

    private fun againButton() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun endGame() {
        binding.lower.setOnClickListener {  }
        binding.bigger.setOnClickListener {  }
        binding.isOk.setOnClickListener {  }

        minValue = 0
        maxValue = 0
        steps = 0
        number = 0
    }
}