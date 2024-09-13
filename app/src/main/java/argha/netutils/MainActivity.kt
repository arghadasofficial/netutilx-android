package argha.netutils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import argha.netutils.screens.DnsScreen
import argha.netutils.ui.theme.NetUtilXTheme
import argha.netutils.viewmodels.DnsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val dnsViewModel: DnsViewModel = viewModel()
            NetUtilXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DnsScreen(innerPadding = innerPadding, dnsViewModel = dnsViewModel)
                }
            }
        }
    }
}