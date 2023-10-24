package com.globa.prodmir.login.internal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.globa.prodmir.common.theme.ProdmirTheme
import com.globa.prodmir.login.R

@Composable
internal fun ErrorComposable(
    modifier: Modifier = Modifier,
    message: String,
    onReturnButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = message,
            modifier = Modifier.align(Alignment.Center)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            onClick = {onReturnButtonClick()},
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = stringResource(R.string.return_text))
        }
    }
}

@Preview
@Composable
fun ErrorComposablePreview() {
    ProdmirTheme {
        Surface(
            modifier = Modifier.size(width = 360.dp, height = 480.dp)
        ) {
            ErrorComposable(message = "404: Test error!") {
                
            }
        }
    }
}