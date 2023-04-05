package com.example.parseerrorviewmodellivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {

    private val selectedImage = MutableLiveData<Int>()

    private val images = MutableLiveData<IntArray>()

    fun setSelectedImage(imageId: Int){
        selectedImage.value = imageId
    }

    fun getSelectedImage() : LiveData<Int> {
        return selectedImage
    }

    fun setImages(imagesArray: IntArray){
        this.images.value = imagesArray
    }

    fun getImages(): LiveData<IntArray> {
        return images
    }
}