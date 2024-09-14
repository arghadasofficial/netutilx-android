package argha.netutils.api

import argha.netutils.models.auth.ApiKeyResponseCreate
import argha.netutils.models.auth.ApiKeyResponseGet
import argha.netutils.models.auth.LoginResponse
import argha.netutils.models.auth.RegisterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthAPI {

    @POST("auth/login.php")
    suspend fun login(email: String, password: String) : Response<LoginResponse>

    @POST("auth/register.php")
    suspend fun register(email: String, password: String, name: String, username: String) : Response<RegisterResponse>

    @GET("auth/getApiDetails.php")
    suspend fun getApiDetails(userId: String) : Response<ApiKeyResponseGet>

    @POST("auth/createApiKey.php")
    suspend fun createApiKey(userId: String) : Response<ApiKeyResponseCreate>
}