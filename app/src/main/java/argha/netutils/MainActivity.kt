package argha.netutils

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import argha.netutils.api.AuthAPI
import argha.netutils.api.DnsAPI
import argha.netutils.screens.AuthScreen
import argha.netutils.ui.theme.NetUtilXTheme
import argha.netutils.viewmodels.AuthViewModel
import argha.netutils.viewmodels.DnsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dnsApi: DnsAPI

    @Inject
    lateinit var authAPI: AuthAPI

    private val apiKey = "7de71c8035945809ef7ba64cd9264570498dc1186f8dcf78663c1fa4a2d423de"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            val types = dnsApi.fetchDnsTypes(apiKey)
            val servers = dnsApi.fetchQueryServers(apiKey)
            val dnsAllInfo = dnsApi.fetchAllDnsInfo(apiKey, "1", "crudoimage.com")
            val dnsInfo = dnsApi.fetchDnsInfo(apiKey, "1", "A", "crudoimage.com")
            val soaInfo = dnsApi.fetchSoaInfo(apiKey, "1", "soa", "crudoimage.com")

            val loginResponse = authAPI.login("arghadasofficial@gmail.com", "myaccountdeveloper")
            val registerResponse = authAPI.register("arghadasofficial@gmail.com", "myaccountdeveloper", "Argha Das", "arghadasofficial")
            val apiDetailsResponse = authAPI.getApiDetails("1")
            val createApiKeyResponse = authAPI.createApiKey("1")

            Log.d("TYPES", types.body().toString())
            Log.d("SERVERS", servers.body().toString())
            Log.d("ALL", dnsAllInfo.body().toString())
            Log.d("INFO", dnsInfo.body().toString())
            Log.d("SOA", soaInfo.body().toString())
            Log.d("LOGIN", loginResponse.body().toString())
            Log.d("REGISTER", registerResponse.body().toString())
            Log.d("API DETAILS", apiDetailsResponse.body().toString())
            Log.d("CREATE API KEY", createApiKeyResponse.body().toString())
        }
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val dnsViewModel: DnsViewModel = viewModel()
            val authViewModel: AuthViewModel = viewModel()
            NetUtilXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AuthScreen(innerPadding = innerPadding, viewModel = authViewModel)
                }
            }
        }
    }
}