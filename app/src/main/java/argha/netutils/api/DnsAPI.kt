package argha.netutils.api

import argha.netutils.models.dnsx.DnsInfo
import argha.netutils.models.dnsx.DnsServers
import argha.netutils.models.dnsx.DnsTypes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DnsAPI {

    @GET("dnsx/get_servers.php?")
    suspend fun fetchTypes(
        @Query("api_key") apiKey: String
    ) : Response<DnsServers>

    @GET("dnsx/get_types.php?")
    suspend fun fetchServers(
        @Query("api_key") apiKey: String
    ) : Response<DnsTypes>

    suspend fun fetchDnsInfo() : Response<DnsInfo>
}