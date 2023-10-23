package com.globa.prodmir.main.internal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.globa.prodmir.main.R

@Composable
fun LoggedInComposable(
    modifier: Modifier = Modifier,
    token: String,
    onLogoutButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                top = 24.dp,
                bottom = 24.dp,
                end = 16.dp
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Token: $token"
        )
        Button(
            onClick = { onLogoutButtonClick() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = stringResource(R.string.logout))
        }
    }
}

@Preview
@Composable
fun LoggedInComposablePreview() {
    ProdmirTheme {
        Surface {
            LoggedInComposable(
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwicGhvbmUiOiIzNzUyOTMwODIzNTgiLCJjaGVjayI6NTQ1NTU0LCJhdHRlbXB0IjoxLCJjaGVja0F0dGVtcHQiOjAsImlhdCI6MTY1Mzg5OTQ1MSwiZXhwIjoxNjU0NTA0MjUxfQ.3UsAr_TadZbtVYjNa9o5yG0mP3zinv3IZeTPv8NGxmA"
            ) {}
        }

    }
}