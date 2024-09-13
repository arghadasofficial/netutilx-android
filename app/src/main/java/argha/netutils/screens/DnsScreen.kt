package argha.netutils.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import argha.netutils.models.dnsx.DnsAllInfo
import argha.netutils.models.dnsx.DnsInfo
import argha.netutils.models.dnsx.DnsServers
import argha.netutils.models.dnsx.DnsTypes
import argha.netutils.models.dnsx.SOAInfo
import argha.netutils.viewmodels.DnsViewModel

@Composable
fun DnsScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    dnsViewModel: DnsViewModel = viewModel()
) {
    val dnsTypes: State<DnsTypes> = dnsViewModel.dnsTypes.collectAsState()
    val dnsQueryServers: State<DnsServers> = dnsViewModel.dnsQueryServers.collectAsState()
    val dnsInfo: State<DnsInfo> = dnsViewModel.dnsInfo.collectAsState()
    val soaInfo: State<SOAInfo> = dnsViewModel.soaInfo.collectAsState()
    val allDnsInfo: State<DnsAllInfo> = dnsViewModel.allDnsInfo.collectAsState()

    LazyColumn(contentPadding = innerPadding, modifier = modifier) {
        item {
            Text("DNS Types")
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                dnsTypes.value.data.forEach { type ->
                    Text("Type: $type ")
                }
            }
        }

        item {
            Text("DNS Query Servers")
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                dnsQueryServers.value.data.forEach { server ->
                    Text("Servers: ${server.name} ")
                }
            }
        }

        item {
            Text("DNS Info")
            Column {
                dnsInfo.value.data.forEach { info ->
                    Text(
                        "Name: ${info.name}, TTL: ${info.ttl}, Class: ${info.classX}, " +
                                "Type: ${info.type}, Data: ${info.data} "
                    )
                }
            }
        }

        item {
            Text("SOA Info")
            Column {
                soaInfo.value.data.forEach { info ->
                    Text(
                        "Primary NS: ${info.data.primaryNameserver}, Responsible: " +
                                "${info.data.responsibleAuthority}, Serial: ${info.data.serial} "
                    )
                }
            }
        }

        item {
            Text("All DNS Info")
            Column {
                allDnsInfo.value.data.a.forEach { aRecord ->
                    Text("A: ${aRecord.data}")
                }
                allDnsInfo.value.data.ns.forEach { nsRecord ->
                    Text("NS: ${nsRecord.data}")
                }
                allDnsInfo.value.data.mx.forEach { mxRecord ->
                    Text("MX: ${mxRecord.data}")
                }
                allDnsInfo.value.data.txt.forEach { txtRecord ->
                    Text("TXT: ${txtRecord.data}")
                }
                allDnsInfo.value.data.soa.forEach { soaRecord ->
                    Text("SOA: ${soaRecord.data.primaryNameserver}")
                }
            }
        }
    }
}