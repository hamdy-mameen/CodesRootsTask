package com.example.codesrootstask.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.codesrootstask.data.Resource
import com.example.codesrootstask.isInternetAvailable
import com.example.codesrootstask.repositories.MainRepository
import com.example.codesrootstask.responses.ads.AdsResponse
import com.example.codesrootstask.responses.category.CategoryResponse
import com.example.codesrootstask.responses.home.HomeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val app:Application,
    val repository: MainRepository
):AndroidViewModel(app){
    private val _adsLiveData:MutableLiveData<Resource<AdsResponse>> = MutableLiveData()
    val adsLiveData:LiveData<Resource<AdsResponse>>
    get() = _adsLiveData

    private val _categoryLiveData:MutableLiveData<Resource<CategoryResponse>> = MutableLiveData()
    val categoryLiveData:LiveData<Resource<CategoryResponse>>
    get() = _categoryLiveData

    private val _mainLiveData:MutableLiveData<Resource<HomeResponse>> = MutableLiveData()
    val mainLiveData:LiveData<Resource<HomeResponse>>
        get() = _mainLiveData
init {
    getAds()
    getCategories()
    getMainHome()
}

    private fun getAds() = viewModelScope.launch {
        try {
           if (isInternetAvailable(app)){
               _adsLiveData.postValue(Resource.Loading())
               val response = repository.getAds()
               if (response.isSuccessful){
                   response.body()?.let {
                       _adsLiveData.postValue(Resource.Success(it))
                   }
               }else{
                   _adsLiveData.postValue(Resource.Failure(response.message()))
               }
           }else{
               _adsLiveData.postValue(Resource.Failure("انت غير متصل بالانترنت"))
           }
        }catch (throwable:Throwable){
         when(throwable){
             is IOException -> _adsLiveData.postValue(Resource.Failure("Error"))
             else -> {_adsLiveData.postValue(Resource.Failure("wrong"))
             Log.d("TAG","main error: ${throwable.message}")}
         }
        }
    }

    private fun getCategories() = viewModelScope.launch {
        try {
            if (isInternetAvailable(app)){
                _categoryLiveData.postValue(Resource.Loading())
                val response = repository.getCategories()
                if (response.isSuccessful){
                    response.body()?.let {
                        _categoryLiveData.postValue(Resource.Success(it))
                    }
                }else{
                    _categoryLiveData.postValue(Resource.Failure(response.message()))
                }
            }else{
                _categoryLiveData.postValue(Resource.Failure("انت غير متصل بالانترنت"))
            }
        }catch (throwable:Throwable){
            when(throwable){
                is IOException -> _categoryLiveData.postValue(Resource.Failure("Error"))
                else -> _categoryLiveData.postValue(Resource.Failure("wrong"))
            }
        }
    }

    private fun getMainHome() = viewModelScope.launch {
        try {
            if (isInternetAvailable(app)){
                _mainLiveData.postValue(Resource.Loading())
                val response = repository.getMainHome()
                if (response.isSuccessful){
                    response.body()?.let {
                        _mainLiveData.postValue(Resource.Success(it))
                    }
                }else{
                    _mainLiveData.postValue(Resource.Failure(response.message()))
                }
            }else{
                _mainLiveData.postValue(Resource.Failure("انت غير متصل بالانترنت"))
            }
        }catch (throwable:Throwable){
            when(throwable){
                is IOException -> _mainLiveData.postValue(Resource.Failure("Error"))
                else -> {
                    _mainLiveData.postValue(Resource.Failure("wrong ${throwable.message}"))
                    Log.d("TAG","main error: ${throwable.message}")
                }
            }
        }
    }
}