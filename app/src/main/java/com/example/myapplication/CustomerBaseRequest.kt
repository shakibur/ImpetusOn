package com.example.myapplication

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class CustomerBaseRequest {
    @SerializedName("Authorization")
    @Expose
    var authorization: CustomerDetailsRequest? = null
}