package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.network.Amphibian


@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(vertical = 12.dp)) {
        AmphibianCardHeader(amphibian = amphibian)
        AmphibianImage(imgSrc = amphibian.img_src)
        Text(text = amphibian.description, modifier = Modifier.padding(12.dp))
    }
}

@Composable
fun AmphibianCardHeader(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(12.dp)) {
        Text(
            text = stringResource(id = R.string.amphibian_type, amphibian.name, amphibian.type),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun AmphibianImage(imgSrc: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imgSrc).crossfade(true)
            .build(),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
            .padding(0.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun AmphibianList(amphibians: List<Amphibian>, modifier: Modifier = Modifier) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 12.dp), modifier = modifier) {
        items(items = amphibians, key = { amphibian -> amphibian.name }) { item ->
            AmphibianCard(amphibian = item)
        }
    }
}