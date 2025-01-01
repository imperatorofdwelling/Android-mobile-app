package com.imperatorofdwelling.android.data.entities

import com.google.gson.annotations.SerializedName

class ServerDataListAnswer <T> {
    @SerializedName("data")
    var data: List<T>? = null
}