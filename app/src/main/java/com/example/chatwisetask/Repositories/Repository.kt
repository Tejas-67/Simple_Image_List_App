package com.example.chatwisetask.Repositories

import com.example.chatwisetask.API.RetrofitInstance
import com.example.chatwisetask.DataModel.Images
import retrofit2.Response

class Repository {

    suspend fun getImages(): Response<Images> {
        return RetrofitInstance.api.getImages()
    }
}