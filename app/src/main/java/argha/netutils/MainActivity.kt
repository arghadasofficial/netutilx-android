package argha.netutils

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import argha.netutils.api.DnsAPI
import argha.netutils.ui.theme.NetUtilXTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dnsXAPI: DnsAPI

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        GlobalScope.launch {
            val serverResponse =
                dnsXAPI.fetchServers("7de71c8035945809ef7ba64cd9264570498dc1186f8dcf78663c1fa4a2d423de")
            val typesServer =
                dnsXAPI.fetchTypes("7de71c8035945809ef7ba64cd9264570498dc1186f8dcf78663c1fa4a2d423de")
            val aResponse =
                dnsXAPI.fetchDnsInfo(
                    "7de71c8035945809ef7ba64cd9264570498dc1186f8dcf78663c1fa4a2d423de",
                    "1",
                    "a",
                    "crudoimage.com"
                )

            val nsResponse =
                dnsXAPI.fetchDnsInfo(
                    "7de71c8035945809ef7ba64cd9264570498dc1186f8dcf78663c1fa4a2d423de",
                    "1",
                    "ns",
                    "crudoimage.com"
                )

            val mxResponse =
                dnsXAPI.fetchDnsInfo(
                    "7de71c8035945809ef7ba64cd9264570498dc1186f8dcf78663c1fa4a2d423de",
                    "1",
                    "mx",
                    "crudoimage.com"
                )

            val txtResponse =
                dnsXAPI.fetchDnsInfo(
                    "7de71c8035945809ef7ba64cd9264570498dc1186f8dcf78663c1fa4a2d423de",
                    "1",
                    "txt",
                    "crudoimage.com"
                )

            val soaResponse =
                dnsXAPI.fetchSoaInfo(
                    "7de71c8035945809ef7ba64cd9264570498dc1186f8dcf78663c1fa4a2d423de",
                    "1",
                    "soa",
                    "crudoimage.com"
                )

            val allResponse =
                dnsXAPI.fetchAllDnsInfo(
                    "7de71c8035945809ef7ba64cd9264570498dc1186f8dcf78663c1fa4a2d423de",
                    "1",
                    "crudoimage.com"
                )
            Log.d("SERVER_RESPONSE", serverResponse.body().toString())
            Log.d("TYPES_RESPONSE", typesServer.body().toString())
            Log.d("A", aResponse.body().toString())
            Log.d("NS", nsResponse.body().toString())
            Log.d("MX", mxResponse.body().toString())
            Log.d("TXT", txtResponse.body().toString())
            Log.d("SOA", soaResponse.body().toString())
            Log.d("ALL", allResponse.body().toString())
        }
        setContent {
            NetUtilXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NetUtilXTheme {
        Greeting("Android")
    }
}