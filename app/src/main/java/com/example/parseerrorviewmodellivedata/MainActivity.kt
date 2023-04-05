package com.example.parseerrorviewmodellivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewModel = ViewModelProvider(this)[ImageViewModel::class.java]

        val dessert_images = intArrayOf(
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven,
            R.drawable.eight,
            R.drawable.nine,
            R.drawable.ten,
            R.drawable.eleven,
            R.drawable.twelve,
        )


        imageViewModel.setSelectedImage(R.drawable.one)
        imageViewModel.setImages(dessert_images)

        val fragment1 = ImageListFragment()
        val fragment2 = ImageDisplayFragment()

        if(savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.imageFragmentContainer1, fragment1)
                .add(R.id.imageFragmentContainer2, fragment2)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
    }
}