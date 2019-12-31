package com.example.myapplication.network

import com.example.myapplication.LoginForm
import com.example.myapplication.TokenResponse
import com.example.myapplication.network.UserInfo
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import retrofit2.Call
import retrofit2.Callback


fun login(userService: UserService, user: LoginForm, onSuccess: (String) -> Unit, onError: (String) -> Unit)
{
    val request = userService.login(user)
    request.enqueue(
        object : Callback<TokenResponse> {
            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                onError(t.message ?: "Unknown error")
            }

            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if (!response.isSuccessful || response.body() == null) {
                    onError(response.message())
                } else
                    onSuccess(response.body()!!.token)
            }
        }
    )
}




interface UserService {
    @GET("users/info")
    suspend fun getInfo(): Response<UserInfo>

    @Multipart
    @PATCH("users/update_avatar")
    suspend fun updateAvatar(@Part avatar: MultipartBody.Part): Response<UserInfo>

    @POST("users/login")
    fun login(@Body user: LoginForm): Call<TokenResponse>

}