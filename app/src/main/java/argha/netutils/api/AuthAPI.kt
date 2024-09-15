package argha.netutils.api

import argha.netutils.models.auth.ApiKeyResponseCreate
import argha.netutils.models.auth.ApiKeyResponseGet
import argha.netutils.models.auth.LoginResponse
import argha.netutils.models.auth.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthAPI {

    @FormUrlEncoded
    @POST("auth/login.php")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("auth/register.php")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("username") username: String
    ): Response<RegisterResponse>

    @GET("auth/getApiDetails.php")
    suspend fun getApiDetails(@Query("user_id") userId: String): Response<ApiKeyResponseGet>

    @FormUrlEncoded
    @POST("auth/createApiKey.php")
    suspend fun createApiKey(@Field("user_id") userId: String): Response<ApiKeyResponseCreate>
}