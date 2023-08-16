package com.mentoria.chuck_norris_jokes

import com.google.gson.annotations.SerializedName

data class Joke(

    @SerializedName("icon_url")
    val icon_url: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("value")
    val value: String
)