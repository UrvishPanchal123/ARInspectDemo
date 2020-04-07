package com.arinspect.demo.network.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.arinspect.demo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataModel {

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("rows")
    @Expose
    var data: List<Data>? = null

    class Data {

        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("imageHref")
        @Expose
        var imagePath: String? = null
    }

    companion object {

        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun ImageView.loadImage(url: String?) {
            Glide.with(context)
                .load(url)
                .error(R.mipmap.ic_launcher_round)
                .fallback(R.mipmap.ic_launcher_round)
                .apply(RequestOptions().circleCrop())
                .into(this)
        }
    }
}