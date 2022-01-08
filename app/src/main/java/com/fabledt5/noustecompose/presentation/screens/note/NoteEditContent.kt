package com.fabledt5.noustecompose.presentation.screens.note

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.noustecompose.R
import com.fabledt5.noustecompose.presentation.theme.selectedGradientColor
import com.fabledt5.noustecompose.presentation.theme.textFieldCursorColor
import com.fabledt5.noustecompose.presentation.theme.textFieldTextColor
import com.fabledt5.noustecompose.presentation.utils.Constants.IMAGE_PICK_INTENT
import com.fabledt5.noustecompose.presentation.utils.Gradient
import com.fabledt5.noustecompose.presentation.utils.toVerticalGradient

@Composable
fun NoteEditContent(
    title: String,
    onTitleChange: (String) -> Unit,
    noteText: String,
    onNoteTextChange: (String) -> Unit,
    noteGradient: Gradient,
    onGradientChange: (Gradient) -> Unit,
    noteImage: Bitmap?,
    onNoteImageChange: (Bitmap?) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { onTitleChange(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.name),
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    cursorColor = textFieldCursorColor
                ),
                textStyle = TextStyle(
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.textFieldTextColor
                ),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )
            OutlinedTextField(
                value = noteText,
                onValueChange = { onNoteTextChange(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(R.string.write_something),
                        color = Color.LightGray,
                        fontSize = 20.sp
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    cursorColor = textFieldCursorColor
                ),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.textFieldTextColor
                ),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )
            if (noteImage != null) {
                Image(
                    bitmap = noteImage.asImageBitmap(),
                    contentDescription = stringResource(R.string.note_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 70.dp, start = 15.dp, end = 15.dp)
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(8.dp))
                        .align(CenterHorizontally)
                )
            }
        }
        AdditionalContent(
            noteGradient = noteGradient,
            onGradientChange = onGradientChange,
            onNoteImageChange = onNoteImageChange
        )
    }
}

@Suppress("DEPRECATION")
@Composable
fun BoxScope.AdditionalContent(
    noteGradient: Gradient,
    onGradientChange: (Gradient) -> Unit,
    onNoteImageChange: (Bitmap?) -> Unit,
) {
    var isGradientsListOpen by remember { mutableStateOf(false) }
    val angle by animateFloatAsState(
        targetValue = if (isGradientsListOpen) 180f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 60.dp)
            .background(color = MaterialTheme.colors.background)
            .align(alignment = Alignment.BottomCenter)
            .horizontalScroll(state = rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        PickImageButton(onNoteImageChange = onNoteImageChange)
        Spacer(modifier = Modifier.width(10.dp))
        Canvas(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.selectedGradientColor,
                    shape = CircleShape
                )
        ) {
            drawCircle(brush = noteGradient.toVerticalGradient())
        }
        GradientsList(isOpened = isGradientsListOpen, onGradientSelected = { onGradientChange(it) })
        IconButton(onClick = { isGradientsListOpen = !isGradientsListOpen }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = stringResource(R.string.icon_gradients_list),
                modifier = Modifier
                    .size(34.dp)
                    .rotate(degrees = angle),
                tint = Color.LightGray
            )
        }
    }
}

@Suppress("DEPRECATION")
@Composable
fun PickImageButton(onNoteImageChange: (Bitmap?) -> Unit) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        val bitmap = uri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            } ?: null
        }
        onNoteImageChange(bitmap)
    }

    IconButton(onClick = { launcher.launch(IMAGE_PICK_INTENT) }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_select_image),
            contentDescription = stringResource(R.string.icon_select_image)
        )
    }
}

@Composable
fun GradientsList(isOpened: Boolean, onGradientSelected: (Gradient) -> Unit) {
    val gradientsList = Gradient.values()
    Row(
        modifier = Modifier
            .height(28.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        if (isOpened) {
            repeat(gradientsList.size) { index ->
                Spacer(modifier = Modifier.width(10.dp))
                Canvas(modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .clickable { onGradientSelected(gradientsList[index]) }
                ) {
                    drawCircle(brush = gradientsList[index].toVerticalGradient())
                }
            }
        }
    }
}
