package com.globa.prodmir.login.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.globa.prodmir.common.theme.ProdmirTheme
import com.globa.prodmir.login.R

@Composable
fun SMSScreenComposable(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    smsCode: String,
    timeout: Int,
    onSmsCodeChange: (String) -> Unit,
    onContinueButtonClick: () -> Unit,
    onNewSmsRequestClick: () -> Unit
) {
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
                text = stringResource(R.string.sms_code),
                style = MaterialTheme.typography.titleMedium
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = smsCode,
                    onValueChange = {onSmsCodeChange(it)},
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        unfocusedIndicatorColor =
                        if (smsCode.isEmpty())
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
                                    .clickable { onSmsCodeChange("") }
                            )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = PhoneVisualTransformation(
                        mask = "00-00-00",
                        maskNumber = '0'
                    )
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            append(stringResource(R.string.send_to_the_number))
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                letterSpacing = 0.15.sp
                            )
                        ) {
                            append(text = "+375 (44) $phoneNumber") //TODO: visual transformation?
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)
                )
                if (timeout != 0) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.surfaceVariant
                                )
                            ) {
                                append(stringResource(R.string.sms_timeout_1))
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    letterSpacing = 0.15.sp
                                )
                            ) {
                                append(text = timeout.toString())
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.surfaceVariant
                                )
                            ) {
                                append(stringResource(R.string.sms_timeout_2))
                            }
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    )
                } else {
                    Text(
                        text = stringResource(R.string.send_new_code),
                        color = MaterialTheme.colorScheme.primary,
                        style = TextStyle(fontWeight = FontWeight.Medium),
                        modifier = Modifier.clickable {
                            onNewSmsRequestClick()
                        }
                    )
                }
            }

        }

        if (smsCode.length == 6) {
            Button(
                onClick = { onContinueButtonClick() },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = stringResource(R.string.continue_text),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun SMSScreenComposablePreview() {
    ProdmirTheme {
        Surface {
            SMSScreenComposable(
                phoneNumber = "447479953",
                smsCode = "",
                timeout = 58,
                onSmsCodeChange = {},
                onContinueButtonClick = {},
                onNewSmsRequestClick = {}
            )
        }
    }
}