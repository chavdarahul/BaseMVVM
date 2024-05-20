package com.example.practiceapp.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceapp.data.repository.TestRepository
import com.example.practiceapp.data.api.Resource
import com.example.practiceapp.data.model.GetUserListResponse
import com.example.practiceapp.data.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TestViewModel @Inject constructor(
    private val repository: TestRepository
) : ViewModel() {

    //Get Item Details
    private val _getUsersResponse: MutableLiveData<Resource<GetUserListResponse>> =
        MutableLiveData()

    val getUsersResponse: LiveData<Resource<GetUserListResponse>>
        get() = _getUsersResponse

    fun getUsers(page: Int) = viewModelScope.launch {
        _getUsersResponse.value = Resource.Loading
        _getUsersResponse.value =
            repository.getUsers(page)
    }

    fun insertUserData(data: UserData) = viewModelScope.launch {
        repository.insertUserData(data)
    }

    fun getUserData():LiveData<List<UserData>> {
        return repository.getUserData()
    }
}