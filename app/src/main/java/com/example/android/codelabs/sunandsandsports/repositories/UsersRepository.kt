package com.example.android.codelabs.sunandsandsports.repositories

import com.example.android.codelabs.sunandsandsports.network.UsersApi
import net.simplifiedcoding.data.repositories.SafeApiRequest


class UsersRepository(
    private val api: UsersApi
) : SafeApiRequest() {

    suspend fun getUsers(page:Int,result:Int) = apiRequest {
        api.getUsers(page,result)}

}