package com.imperatorofdwelling.android.data.entities

import com.google.gson.annotations.SerializedName

class ServerDataAnswer <T> {
    @SerializedName("data")
    var data: T? = null
}