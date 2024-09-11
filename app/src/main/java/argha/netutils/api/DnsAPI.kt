package argha.netutils.api

import argha.netutils.models.dnsx.DnsAllInfo
import argha.netutils.models.dnsx.DnsInfo
import argha.netutils.models.dnsx.DnsServers
import argha.netutils.models.dnsx.DnsTypes
import argha.netutils.models.dnsx.SOAInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DnsAPI {

    @GET("dnsx/get_servers.php?")
    suspend fun fetchTypes(
        @Query("api_key") apiKey: String
    ): Response<DnsServers>

    @GET("dnsx/get_types.php?")
    suspend fun fetchServers(
        @Query("api_key") apiKey: String
    ): Response<DnsTypes>

    @GET("dnsx/get_dns_info.php?")
    suspend fun fetchDnsInfo(
        @Query("api_key") apiKey: String,
        @Query("server") server: String,
        @Query("type") type: String,
        @Query("query") query: String
    ): Response<DnsInfo>

    @GET("dnsx/get_dns_info.php?")
    suspend fun fetchSoaInfo(
        @Query("api_key") apiKey: String,
        @Query("server") server: String,
        @Query("type") type: String,
        @Query("query") query: String
    ): Response<SOAInfo>

    @GET("dnsx/get_all_info.php?")
    suspend fun fetchAllDnsInfo(
        @Query("api_key") apiKey: String,
        @Query("server") server: String,
        @Query("query") query: String
    ): Response<DnsAllInfo>
}