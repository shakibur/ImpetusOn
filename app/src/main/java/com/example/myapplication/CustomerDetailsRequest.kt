package com.example.myapplication

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class CustomerDetailsRequest {

    @SerializedName("Customer")
    @Expose
    var customer: String? = null

    @SerializedName("Language")
    @Expose
    var language: String? = null

    @SerializedName("Currency")
    @Expose
    var currency: String? = null

    @SerializedName("OrderName")
    @Expose
    var orderName: String? = null

    @SerializedName("OrderID")
    @Expose
    var orderID: String? = null

    @SerializedName("Channel")
    @Expose
    var channel: String? = null

    @SerializedName("Amount")
    @Expose
    var amount: String? = null

    @SerializedName("TransactionHint")
    @Expose
    var transactionHint: String? = null

    @SerializedName("CardNumber")
    @Expose
    var cardNumber: String? = null

    @SerializedName("ExpiryMonth")
    @Expose
    var expiryMonth: String? = null

    @SerializedName("ExpiryYear")
    @Expose
    var expiryYear: String? = null

    @SerializedName("VerifyCode")
    @Expose
    var verifyCode: String? = null

    @SerializedName("UserName")
    @Expose
    var userName: String? = null

    @SerializedName("Password")
    @Expose
    var password: String? = null

}