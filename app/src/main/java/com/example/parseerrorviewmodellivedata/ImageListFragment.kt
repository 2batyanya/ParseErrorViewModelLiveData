package com.example.parseerrorviewmodellivedata

import android.os.Bundle
import android.service.autofill.CustomDescription
import android.service.autofill.FillCallback
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val IMAGE_ARRAY_KEY = "imagearraykey"

class ImageListFragment : Fragment() {

    private lateinit var images: IntArray

    lateinit var imageViewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        imageViewModel = ViewModelProvider(requireActivity())[ImageViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view as RecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 2)

            imageViewModel.getImages().observe(requireActivity()) { it ->
                adapter = CustomRecuclerAdapter(it) {
                    imageViewModel.setSelectedImage(it)
                }
            }

        }
    }

}

class CustomRecuclerAdapter(private val items: IntArray, private val callback: (Int) -> Unit) :
    RecyclerView.Adapter<CustomRecuclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        init {
            imageView.setOnClickListener { callback(items[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(450, 450)
            setPadding(20, 0, 20, 0)
        })


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.imageView.setImageResource(items[position])


    override fun getItemCount() = items.size
}