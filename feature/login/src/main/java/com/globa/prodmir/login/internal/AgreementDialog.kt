package com.globa.prodmir.login.internal

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.globa.prodmir.common.theme.ProdmirTheme

@Composable
fun AgreementDialog(
    onRead: () -> Unit
) {
    val url = "https://yoda.by/about-the-service/personal-data"
    val context = LocalContext.current
    Dialog(
        onDismissRequest = { onRead() },
    ) {
        AndroidView(factory = {
            WebView(context).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        })
    }
}

@Preview
@Composable
fun AgreementDialogPreview() {
    ProdmirTheme {
        Surface {
            AgreementDialog(
                onRead = {}
            )
        }
    }
}