package com.starwarsapp.uicomponents.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.starwarsapp.uicomponents.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SWTopAppBar(
    title: String,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null,
) {
    TopAppBar(
        title = { Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = Ellipsis,
            modifier = Modifier.semantics { heading() }
        ) },
        navigationIcon =
            {
                if (showBackButton && onBackClick != null) {
                    IconButton(
                        onClick = { onBackClick() },
                        modifier = Modifier.focusBorder()
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back_arrow),
                            contentDescription = "Back"
                        )
                    }
                }
            },
    )
}

@PreviewLightDark
@Composable
internal fun PreviewSWTopAppBarBackButton() {
    PreviewSurface {
        SWTopAppBar(
            title = "Star Wars Characters",
            showBackButton = true,
            onBackClick = {}
        )
    }
}

@PreviewLightDark
@Composable
internal fun PreviewSWTopAppBar() {
    PreviewSurface {
        SWTopAppBar(
            title = "Star Wars Characters",
            showBackButton = false,
            onBackClick = {}
        )
    }
}