package com.example.searchinfo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchinfo.data.ImageResponse
import com.example.searchinfo.repository.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val retrofitRepository : RetrofitRepository
): ViewModel() {

    private val _assamble = MutableLiveData<Response<ImageResponse>>()

    val assamble : LiveData<Response<ImageResponse>> = _assamble

    fun getResult(query: String, sort: String) {
        viewModelScope.launch {
            val active = retrofitRepository.productApi(query, sort)
            active.let { data ->
                _assamble.value = data
            }
        }
    }
}