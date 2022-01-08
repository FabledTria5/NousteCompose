package com.fabledt5.noustecompose.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fabledt5.noustecompose.R

@Composable
fun CircleCheckBox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onChecked: () -> Unit = {},
    color: Color
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable { onChecked() }
            .border(
                width = if (isChecked) 0.dp else 1.dp,
                color = if (!isChecked) color else Color.Transparent,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isChecked)
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = stringResource(R.string.icon_check),
                tint = color
            )
    }
}