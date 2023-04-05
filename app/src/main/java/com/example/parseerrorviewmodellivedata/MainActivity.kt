package com.example.parseerrorviewmodellivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private var hasTwoContainers = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        hasTwoContainers = findViewById<View>(R.id.imageFragmentContainer2) != null

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

        val fragment1 = ImageListFragment()

        if(savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.imageFragmentContainer1, fragment1)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()

        if(hasTwoContainers)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.imageFragmentContainer2, ImageDisplayFragment())
                .commit()

        imageViewModel.setImages(dessert_images)

        imageViewModel.getSelectedImage().observe(this){
            if(!imageViewModel.hasSeenSelection && !hasTwoContainers) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.imageFragmentContainer1, ImageDisplayFragment())
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit()

                imageViewModel.hasSeenSelection = true
            }
        }
    }
}