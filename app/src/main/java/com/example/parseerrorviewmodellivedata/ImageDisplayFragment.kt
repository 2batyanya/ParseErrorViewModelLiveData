package com.example.parseerrorviewmodellivedata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider

class ImageDisplayFragment : Fragment() {

    private lateinit var imageView: ImageView

    lateinit var imageViewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageViewModel = ViewModelProvider(requireActivity())[ImageViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_image_display, container, false).apply {
            imageView = findViewById(R.id.imageView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewModel.getSelectedImage().observe(requireActivity()){
            changeImage(it)
        }
    }

    fun changeImage(imageID: Int){
        imageView.setImageResource(imageID)
    }
}