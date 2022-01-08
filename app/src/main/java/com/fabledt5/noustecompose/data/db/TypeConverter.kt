package com.fabledt5.noustecompose.data.db

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.fabledt5.noustecompose.presentation.utils.Gradient
import java.io.ByteArrayOutputStream

object TypeConverter {

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap?): ByteArray? = bitmap?.let {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray?): Bitmap? = byteArray?.let {
        BitmapFactory.decodeByteArray(it, 0, it.size)
    }

    @TypeConverter
    fun fromGradient(gradient: Gradient): String = gradient.name

    @TypeConverter
    fun toGradient(gradientName: String): Gradient = Gradient.valueOf(gradientName)

}