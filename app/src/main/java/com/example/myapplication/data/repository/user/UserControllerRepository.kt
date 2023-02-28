package com.example.myapplication.data.repository.user

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.remote.data.BaseResponse
import com.example.myapplication.data.remote.data.Resource
import com.example.myapplication.data.remote.data.response.apiResponse.NewApiDataItem
import com.example.myapplication.data.repository.BaseRespository
import org.koin.core.KoinComponent

class UserControllerRepository(private val iUserControllerRepository: IUserRepository) :
    KoinComponent, IUserControllerRepository, BaseRespository() {

    override fun getUserProfile(
        response: MutableLiveData<Resource<BaseResponse<ArrayList<NewApiDataItem>>>>
    ) {
        iUserControllerRepository.getUserProfile().enqueue(getCallback(response))
    }

    override fun getCustomerResponse(response: MutableLiveData<Resource<BaseResponse<String>>>) {
    }

    /*override fun getCustomerResponse(response: MutableLiveData<Resource<BaseResponse<String>>>) {
        iUserControllerRepository.getCustomerDetails().enqueue(getCallback(response))
    }*/


}