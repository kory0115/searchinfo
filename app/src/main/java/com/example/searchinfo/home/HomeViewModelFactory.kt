package com.example.searchinfo.home

import com.example.searchinfo.repository.RetrofitRepository

class HomeViewModelFactory(
    private val repository: RetrofitRepository
) /*: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
} */