package com.example.cryptoinfo.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class TopListResponse (
    @SerializedName("Data")
    @Expose
    val data: List<Datum>? = null
)