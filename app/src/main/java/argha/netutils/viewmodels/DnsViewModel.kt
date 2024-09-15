package argha.netutils.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import argha.netutils.models.dnsx.DnsAllInfo
import argha.netutils.models.dnsx.DnsInfo
import argha.netutils.models.dnsx.DnsServers
import argha.netutils.models.dnsx.DnsTypes
import argha.netutils.models.dnsx.SOAInfo
import argha.netutils.repository.DnsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DnsViewModel @Inject constructor(private val dnsRepository: DnsRepository) : ViewModel() {

    val dnsTypes: StateFlow<DnsTypes?> = dnsRepository.dnsTypes
    val dnsQueryServers: StateFlow<DnsServers?> = dnsRepository.dnsQueryServers
    val dnsInfo: StateFlow<DnsInfo?> = dnsRepository.dnsInfo
    val soaInfo: StateFlow<SOAInfo?> = dnsRepository.soaInfo
    val allDnsInfo: StateFlow<DnsAllInfo?> = dnsRepository.allDnsInfo

    private val apiKey = "7de71c8035945809ef7ba64cd9264570498dc1186f8dcf78663c1fa4a2d423de"

    init {
        viewModelScope.launch {
            dnsRepository.getDnsTypes(apiKey)
            dnsRepository.getDnsQueryServers(apiKey)
            dnsRepository.getDnsInfo(apiKey, "1", "A", "crudoimage.com")
            dnsRepository.getSoaInfo(apiKey, "1", "crudoimage.com")
            dnsRepository.getAllDnsInfo(apiKey, "1", "crudoimage.com")
        }
    }
}