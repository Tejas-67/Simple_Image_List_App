package com.example.chatwisetask.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatwisetask.API.Resource
import com.example.chatwisetask.DataModel.Images
import com.example.chatwisetask.Repositories.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ImageViewModel(val repo: Repository): ViewModel() {

    val images: MutableLiveData<Resource<Images>> = MutableLiveData()

    init{
        getImages()
    }
    fun getImages()= viewModelScope.launch{
        images.postValue(Resource.Loading())
        val response = repo.getImages()
        images.postValue(handleResponse(response))
    }

    fun handleResponse(response: Response<Images>): Resource<Images>{
        if(response.isSuccessful){
            response.body()?.let{
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}