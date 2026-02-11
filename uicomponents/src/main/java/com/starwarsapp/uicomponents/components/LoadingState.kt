package com.starwarsapp.uicomponents.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = Ellipsis,
        )
    }
}

@PreviewLightDark
@Composable
internal fun PreviewLoadingStateLightDark() {
    PreviewSurface {
        LoadingState()
    }
}