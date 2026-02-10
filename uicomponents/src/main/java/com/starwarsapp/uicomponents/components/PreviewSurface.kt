package com.starwarsapp.uicomponents.components

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.starwarsapp.uicomponents.theme.StarWarsAppTheme

@Composable
fun PreviewSurface(content: @Composable (() -> Unit)) {
    StarWarsAppTheme {
        Surface {
            content()
        }
    }
}