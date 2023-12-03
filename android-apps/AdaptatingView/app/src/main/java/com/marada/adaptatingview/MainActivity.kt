package com.marada.adaptatingview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.ViewPropertyAnimatorListener
import com.marada.adaptatingview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var adapter: ArrayAdapter<CharSequence>
    private lateinit var binding: ActivityMainBinding
    private lateinit var pictures: List<String>
    private var picturePosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArrayAdapter.createFromResource(this, R.array.pictures, R.layout.item)
        binding.picturesList?.adapter = adapter
        binding.picturesList?.onItemSelectedListener = this
        pictures = resources.getStringArray(R.array.pictures).toList()
    }

    fun onChangePictureClick(v: View) {
        val iv = binding.picture


        picturePosition = (picturePosition + 1) % pictures.size
        val pictureName = pictures[picturePosition].replace(" ", "")
        val img = resources.getIdentifier(pictureName, "drawable", packageName)

        iv.setImageResource(img)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val iv = binding.picture
        Toast.makeText(this, "выбран элемент $position", Toast.LENGTH_SHORT ).show()

        val selectedPicturePosition = resources.getStringArray(R.array.pictures)[position]
        picturePosition = position

        val pictureName = selectedPicturePosition.replace(" ", "")

        val img = resources.getIdentifier(pictureName, "drawable", packageName)
        iv.setImageResource(img)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "не выбран элемент", Toast.LENGTH_SHORT ).show()
    }
}