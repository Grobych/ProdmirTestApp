package com.globa.prodmir.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.globa.prodmir.common.R
import com.globa.prodmir.common.theme.ProdmirTheme

@Composable
fun Header(
    modifier: Modifier = Modifier,
    text: String,
    onBackButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center),
            style = MaterialTheme.typography.titleMedium
        )
        IconButton(
            onClick = { onBackButtonClick() },
            modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
                .align(Alignment.CenterStart)
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = "Back")
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun HeaderPreview() {
    ProdmirTheme {
        Surface {
            Header(
                text = "Login",
                onBackButtonClick = {}
            )
        }
    }
}