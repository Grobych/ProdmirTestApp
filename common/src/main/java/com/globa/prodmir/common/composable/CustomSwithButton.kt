package com.globa.prodmir.common.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.globa.prodmir.common.R
import com.globa.prodmir.common.theme.ProdmirTheme

@Composable
fun CustomSwitchButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    isSelected: Boolean,
    onSelectChange: (Boolean) -> Unit,
    onDisabledClick: () -> Unit
) {
    IconButton(
        modifier = modifier.size(24.dp),
        onClick = {
            if (!isEnabled) onDisabledClick()
            else onSelectChange(isSelected.not())
        }
    ) {
        Icon(
            painter = if (isSelected) painterResource(id = R.drawable.ic_switch_en)
                                 else painterResource(id = R.drawable.ic_switch_dis),
            contentDescription = "Switch",
            tint = if (isEnabled) MaterialTheme.colorScheme.primary
                             else MaterialTheme.colorScheme.surfaceVariant
        )
    }

}

@Preview
@Composable
fun CustomSwitchButtonDisabledPreview() {
    ProdmirTheme {
        Surface {
            CustomSwitchButton(isEnabled = false, isSelected = false, onSelectChange = {} ) {
            }
        }
    }
}

@Preview
@Composable
fun CustomSwitchButtonDisabledSelectedPreview() {
    ProdmirTheme {
        Surface {
            CustomSwitchButton(isEnabled = false, isSelected = true, onSelectChange = {} ) {
            }
        }
    }
}

@Preview
@Composable
fun CustomSwitchButtonEnabledPreview() {
    ProdmirTheme {
        Surface {
            CustomSwitchButton(isEnabled = true, isSelected = false, onSelectChange = {} ) {
            }
        }
    }
}

@Preview
@Composable
fun CustomSwitchButtonSelectedPreview() {
    ProdmirTheme {
        Surface {
            CustomSwitchButton(isEnabled = true, isSelected = true, onSelectChange = {} ) {
            }
        }
    }
}