package argha.netutils.repository

import argha.netutils.api.DnsAPI
import argha.netutils.models.dnsx.DnsAllInfo
import argha.netutils.models.dnsx.DnsInfo
import argha.netutils.models.dnsx.DnsServers
import argha.netutils.models.dnsx.DnsTypes
import argha.netutils.models.dnsx.SOAInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DnsRepository @Inject constructor(private val dnsAPI: DnsAPI) {

    private val _dnsTypes = MutableStateFlow<DnsTypes?>(null)
    val dnsTypes: StateFlow<DnsTypes?> = _dnsTypes

    private val _dnsQueryServers = MutableStateFlow<DnsServers?>(null)
    val dnsQueryServers: StateFlow<DnsServers?> = _dnsQueryServers

    private val _dnsInfo = MutableStateFlow<DnsInfo?>(null)
    val dnsInfo: StateFlow<DnsInfo?> = _dnsInfo

    private val _soaInfo = MutableStateFlow<SOAInfo?>(null)
    val soaInfo: StateFlow<SOAInfo?> = _soaInfo

    private val _allDnsInfo = MutableStateFlow<DnsAllInfo?>(null)
    val allDnsInfo: StateFlow<DnsAllInfo?> = _allDnsInfo

    suspend fun getDnsTypes(apiKey: String) {
        val response = dnsAPI.fetchDnsTypes(apiKey)
        if (response.isSuccessful && response.body() != null) {
            _dnsTypes.emit(response.body()!!)
        }
    }

    suspend fun getDnsQueryServers(apiKey: String) {
        val response = dnsAPI.fetchQueryServers(apiKey)
        if (response.isSuccessful && response.body() != null) {
            _dnsQueryServers.emit(response.body()!!)
        }
    }

    suspend fun getDnsInfo(apiKey: String, server: String, type: String, query: String) {
        val response = dnsAPI.fetchDnsInfo(apiKey, server, type, query)
        if (response.isSuccessful && response.body() != null) {
            _dnsInfo.emit(response.body()!!)
        }
    }

    suspend fun getSoaInfo(apiKey: String, server: String, query: String) {
        val response = dnsAPI.fetchSoaInfo(apiKey, server, "soa", query)
        if (response.isSuccessful && response.body() != null) {
            _soaInfo.emit(response.body()!!)
        }
    }

    suspend fun getAllDnsInfo(apiKey: String, server: String, query: String) {
        val response = dnsAPI.fetchAllDnsInfo(apiKey, server, query)
        if (response.isSuccessful && response.body() != null) {
            _allDnsInfo.emit(response.body()!!)
        }
    }
}