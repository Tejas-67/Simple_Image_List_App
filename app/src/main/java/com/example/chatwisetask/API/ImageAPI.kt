package com.example.chatwisetask.API


import com.example.chatwisetask.DataModel.Images
import retrofit2.Response
import retrofit2.http.GET

interface ImageAPI {

    @GET("/photos")
    suspend fun getImages(): Response<Images>
}