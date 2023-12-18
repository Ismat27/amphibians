package com.example.amphibians

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.ui.screens.AmphibianList
import com.example.amphibians.ui.screens.AmphibianUiState
import com.example.amphibians.ui.screens.AmphibianViewModel

@Composable
fun AmphibianApp(

) {

    Scaffold(topBar = { AmphibianAppTopBar() }) {
        Surface(modifier = Modifier.padding(it).fillMaxSize()) {
            val amphibianViewModel: AmphibianViewModel = viewModel(factory = AmphibianViewModel.Factory)
            when (val amphibianUiState = amphibianViewModel.amphibianUiState) {
                is AmphibianUiState.Loading -> AppLoading()
                is AmphibianUiState.Error -> ErrorLoading(
                    retry = { amphibianViewModel.getAmphibians() }
                )

                is AmphibianUiState.Success -> AmphibianList(amphibians = amphibianUiState.data)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibianAppTopBar(modifier: Modifier = Modifier) {
    TopAppBar(title = {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium
        )
    }, modifier = modifier)
}

@Composable
fun AppLoading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading...")
    }
}

@Composable
fun ErrorLoading(retry: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error loading data")
        Button(onClick = retry) {
            Text(text = "Retry")
        }
    }
}