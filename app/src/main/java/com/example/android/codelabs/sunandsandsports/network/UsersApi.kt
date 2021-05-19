package com.example.android.codelabs.sunandsandsports.network

import com.example.android.codelabs.sunandsandsports.model.UserListResponseModel
import com.example.android.codelabs.sunandsandsports.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("api")
    suspend fun getUsers(@Query("page") page:Int,@Query("results") results : Int) : Response<UserListResponseModel>

    //https://randomuser.me/api/?page=1&results=10
    companion object{

        operator fun invoke() : UsersApi {
            val interceptor=HttpLoggingInterceptor()
            interceptor.level=HttpLoggingInterceptor.Level.BODY
            val okHttpClient:OkHttpClient=OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
                .create(UsersApi::class.java)
        }
    }
}
