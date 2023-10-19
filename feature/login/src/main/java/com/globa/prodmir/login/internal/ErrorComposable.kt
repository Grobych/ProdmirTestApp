package com.globa.prodmir.login.internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
        Button(onClick = {onReturnButtonClick()}) {
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