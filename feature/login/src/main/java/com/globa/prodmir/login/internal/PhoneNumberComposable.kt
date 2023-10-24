package com.globa.prodmir.login.internal

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.globa.prodmir.common.composable.CustomSwitchButton
import com.globa.prodmir.common.theme.ProdmirTheme
import com.globa.prodmir.login.R

@OptIn(ExperimentalTextApi::class)
@Composable
fun PhoneNumberComposable(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    isPhoneNumberError: Boolean,
    isAgreementRead: Boolean,
    isAgreementAccepted: Boolean,
    onTextFieldChange: (String) -> Unit,
    onAgreementCheckButtonClick: (Boolean) -> Unit,
    onContinueButtonClick: () -> Unit,
    showAgreementDialog: Boolean,
    onLinkClicked: () -> Unit,
    onAgreementRead: () -> Unit
) {
    if (showAgreementDialog) {
        AgreementDialog(
            onRead = onAgreementRead
        )
    }
    val context = LocalContext.current
    val personalDataString = stringResource(R.string.personal_data_toast)
    val showToast = fun() {
        Toast.makeText(context, personalDataString, Toast.LENGTH_LONG).show()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 24.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text(
                text = stringResource(R.string.type_phone_number),
                style = MaterialTheme.typography.titleMedium
            )
            TextField(
                value = phoneNumber,
                onValueChange = {onTextFieldChange(it)},
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                singleLine = true,
                isError = isPhoneNumberError,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    unfocusedIndicatorColor =
                    if (phoneNumber.isEmpty())
                        MaterialTheme.colorScheme.surfaceVariant
                    else MaterialTheme.colorScheme.onSurface,
                ),
                trailingIcon = {
                    if (phoneNumber.isNotEmpty())
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Clear",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { onTextFieldChange("") }
                        )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                placeholder = {
                    Text(
                        text = "+375 (XX) XXX-XX-XX",
                        color = MaterialTheme.colorScheme.surfaceVariant
                    )
                },
                visualTransformation = PhoneVisualTransformation(
                    mask = "+375 (00) 000-00-00",
                    maskNumber = '0'
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomSwitchButton(
                    isEnabled = isAgreementRead,
                    isSelected = isAgreementAccepted,
                    onSelectChange = { onAgreementCheckButtonClick(it) },
                    onDisabledClick = {
                        showToast()
                    }
                )
                val tintColor = MaterialTheme.colorScheme.primary
                val baseColor = MaterialTheme.colorScheme.surfaceVariant
                val dataString = stringResource(R.string.personal_data)
                val text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = baseColor
                        )
                    ) {
                        append(stringResource(R.string.agree_of))
                    }
                    withAnnotation("tag", "annotation") {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Medium,
                                color = tintColor
                            )
                        ) {
                            append(dataString)
                        }
                    }
                }

                ClickableText(text) {
                    text.getStringAnnotations(it, it).firstOrNull()?.tag?.let { tag ->
                        if (tag == "tag") {
                            onLinkClicked()
                        }
                    }
                }
            }
        }

        if (isAgreementAccepted && phoneNumber.length == 9) {
            Button(
                onClick = { onContinueButtonClick() },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = stringResource(R.string.continue_text),
                    style = MaterialTheme.typography.titleSmall // TODO: check styles
                )
            }
        }
    }

}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyPhoneNumberComposablePreview() {
    ProdmirTheme {
        Surface(
            modifier = Modifier.size(width = 360.dp, height = 480.dp)
        ) {
            PhoneNumberComposable(
                phoneNumber = "",
                isPhoneNumberError = false,
                isAgreementRead = false,
                isAgreementAccepted = false,
                onContinueButtonClick = {},
                onAgreementCheckButtonClick = {},
                onTextFieldChange = {},
                showAgreementDialog = false,
                onAgreementRead = {},
                onLinkClicked = {}
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PhoneNumberComposablePreview() {
    ProdmirTheme {
        Surface(
            modifier = Modifier.size(width = 360.dp, height = 480.dp)
        ) {
            PhoneNumberComposable(
                phoneNumber = "447479953",
                isPhoneNumberError = false,
                isAgreementRead = true,
                isAgreementAccepted = true,
                onContinueButtonClick = {},
                onAgreementCheckButtonClick = {},
                onTextFieldChange = {},
                showAgreementDialog = false,
                onAgreementRead = {},
                onLinkClicked = {}
            )
        }
    }
}