package com.arinspect.demo.network.model

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
}