package argha.netutils.di

import argha.netutils.api.AuthAPI
import argha.netutils.api.DnsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://development.grow10x.app/api/netutil-x-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesDnsApi(retrofit: Retrofit) : DnsAPI {
        return retrofit.create(DnsAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesAuthApi(retrofit: Retrofit) : AuthAPI {
        return retrofit.create(AuthAPI::class.java)
    }

}