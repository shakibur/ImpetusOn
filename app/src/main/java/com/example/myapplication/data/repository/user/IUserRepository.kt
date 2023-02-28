package com.example.myapplication.data.repository.user

import com.example.myapplication.data.remote.data.BaseResponse
import com.example.myapplication.data.remote.data.response.apiResponse.NewApiDataItem
import retrofit2.Call
import retrofit2.http.*

interface IUserRepository {

    @GET("articles")
    fun getUserProfile(): Call<BaseResponse<ArrayList<NewApiDataItem>>>


    /*@Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("")
    fun getCustomerDetails(
        @Part("date") date: RequestBody,
        @Part("user_type") user_type: RequestBody,
        @Part("school_id") school_id: RequestBody

    *//*{
        "Authorization":{
        "Customer":"Demo Merchant",
        "Language":"en",
        "Currency":"AED",
        "OrderName":"ParleKreamsGold",
        "OrderID":"000000576",
        "Channel":"W",
        "Amount":"28.90",
        "TransactionHint":"CPT:Y;",
        "CardNumber":"4111111111111111",
        "ExpiryMonth":"12",
        "ExpiryYear":"2021",
        "VerifyCode":"123",
        "UserName":"Demo_fY9c",
        "Password":"Comtrust@20182018"
    }
    }*//*

    ): Call<BaseResponse<String>>*/

}