package com.example.test.ui.mainscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.test.domain.entity.Image

@Composable
fun MainScreen(state: MainScreenUiState, onRetryClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when (state) {
            is MainScreenUiState.Error -> ErrorScreen(state.errorMessage, onRetryClick, modifier)
            MainScreenUiState.Loading -> LoadingScreen(modifier)
            is MainScreenUiState.Success -> ImageGridScreen(state, modifier)
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun ImageGridScreen(state: MainScreenUiState.Success, modifier: Modifier = Modifier) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
    ) {
        items(items = state.images, key = { it.uri }) {
            ImageCard(it)
        }
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorScreen(text: String, onRetryClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(40.dp),
    ) {
        Text(text = text, textAlign = TextAlign.Justify, style = MaterialTheme.typography.h6)
        TextButton(onClick = onRetryClick) {
            Text(text = "Retry", style = MaterialTheme.typography.button)
        }
    }
}

@Composable
private fun ImageCard(it: Image) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(it.width.toFloat() / it.height)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(it.uri)
                .crossfade(true)
                .build(),
            contentDescription = null,
        )
    }
}
