package com.example.myapplication.data.repository.user

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.remote.data.BaseResponse
import com.example.myapplication.data.remote.data.Resource
import com.example.myapplication.data.remote.data.response.apiResponse.NewApiDataItem

interface IUserControllerRepository {

    fun getUserProfile(
        response: MutableLiveData<Resource<BaseResponse<ArrayList<NewApiDataItem>>>>
    )

    fun getCustomerResponse(
        response: MutableLiveData<Resource<BaseResponse<String>>>
    )

}