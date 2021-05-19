package com.example.android.codelabs.sunandsandsports.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.codelabs.sunandsandsports.model.UserListResponseModel
import com.example.android.codelabs.sunandsandsports.network.UsersApi
import com.example.android.codelabs.sunandsandsports.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel() : ViewModel() {

     val repository: UsersRepository= UsersRepository(UsersApi())
     val user = MutableLiveData<UserListResponseModel>()
    var loading=MutableLiveData<Boolean>(false)

    fun getUsers(page:Int,result:Int) {
        loading.value=true
        viewModelScope.launch(Dispatchers.Main) {
           user.value= repository.getUsers(page,result)
            loading.value=false
        }
    }
}
