package com.marada.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.marada.memorygame.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select


class PairOfCards(
    val first: Int,
    val second: Int
) {
    public fun isThisPair(other: PairOfCards): Boolean {
        return ((first == other.first && second == other.second) ||
            (second == other.first && first == other.second))
    }
}

class MainActivity : AppCompatActivity() {

    private var row: Int = 2
    private var cols: Int = 4
    private var score: Int = 0

    private lateinit var binding: ActivityMainBinding
    private lateinit var field: GridLayout
    private var openedCount = 0
    private var openedCards = ArrayList<Int>()
    private var pairs = ArrayList<PairOfCards>(row * cols / 2)
    private var card_map = mutableMapOf<Int, Int>()

    private var closeCardsJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        field = binding.field
        initPairs()

        for (i in 0 until row) {
            for (j in 0 until cols) {
                val img = ImageView(this)
                val id = i * cols + j
                img.id = id
                img.setImageResource(R.drawable.card_back)
                img.layoutParams = ViewGroup.LayoutParams(10, 20)
                img.setOnClickListener{ openCard(id) }


                val params = GridLayout.LayoutParams()
                params.width = GridLayout.LayoutParams.WRAP_CONTENT
                params.height = GridLayout.LayoutParams.WRAP_CONTENT
                params.setMargins(8, 8, 8, 8)

                field.addView(img, params)
            }
        }

        binding.startButton.setOnClickListener { restart() }
    }

    private fun initPairs() {
        score = 0
        binding.scoreValue.text = "$score"
        val values = ArrayList<Int>(16)
        for (i in 0 until row * cols) {
            values.add(i)
        }
        values.shuffle()
        Log.d("values", values.toString())
        for (i in 0 until row * cols / 2) {
            val first = values.last()
            values.removeAt(values.size - 1)
            val second = values.last()
            values.removeAt(values.size - 1)
            val card_val = i + 1
            Log.d("set pairs", "$first, $second, card_val: $card_val")
            card_map[first] = card_val
            card_map[second] = card_val
            val pair = PairOfCards(first, second)
            pairs.add(pair)
        }
    }

    private fun openCard(idx: Int) {
        closeCardsJob?.cancel()
        closeCardsJob = null

        if (idx in openedCards) {
            return
        }

        val img = findViewById<ImageView>(idx)
        openedCount++
        openedCards.add(idx)

        if (openedCount > 2) {
            closeAllCards()
            return
        }

        val card_val = card_map[idx]
        Log.d("opened cards", openedCards.toString())
        if (card_val == 1) {
            img.setImageResource(R.drawable.card_1)
        }
        if (card_val == 2) {
            img.setImageResource(R.drawable.card_2)
        }
        if (card_val == 3) {
            img.setImageResource(R.drawable.card_3)
        }
        if (card_val == 4) {
            img.setImageResource(R.drawable.card_4)
        }

        if (openedCount == 2) {
            val curPair = PairOfCards(openedCards[0], openedCards[1])
            for (pair in pairs) {
                if (pair.isThisPair(curPair)) {
                    pairs.remove(pair)
                    openedCards.clear()
                    score++
                    binding.scoreValue.text = "$score"
                    openedCount = 0
                    return
                }
            }
            closeCardsJob = lifecycleScope.launch {
                delay(1000)
                closeAllCards()
            }
        }
    }

    private fun closeAllCards() {
        for (idx in openedCards){
            val img = findViewById<ImageView>(idx)
            img.setImageResource(R.drawable.card_back)
        }
        openedCount = 0
        openedCards.clear()
    }

    private fun restart() {
        for (i in 0 until row * cols) {
            findViewById<ImageView>(i).setImageResource(R.drawable.card_back)
        }
        initPairs()
    }

}