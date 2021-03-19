package com.example.beersapp.ui
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object BindingAdapters {

    @BindingAdapter("image")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
                .load(imageUrl)
                .into(view)
    }


    @BindingAdapter("isVisible")
    @JvmStatic
    fun setVisibility(view: View, isVisible : Boolean) {
        if (isVisible){
            view.visibility = View.VISIBLE
        }else{
            view.visibility = View.GONE
        }
    }
}