package com.ezadetoro.studyhelper.utils

import com.ezadetoro.studyhelper.presentation.theme.Green
import com.ezadetoro.studyhelper.presentation.theme.Orange
import com.ezadetoro.studyhelper.presentation.theme.Red

enum class Priority(val title: String, val color: androidx.compose.ui.graphics.Color, val value: Int){
    LOW(title = "Low", color = Green,  value =0),
    MEDIUM(title = "Low", color = Orange,  value =1),
    HIGH(title = "Low", color = Red,  value =2);

    companion object {
        fun fromInt(value: Int) = values().firstOrNull { it.value == value } ?: MEDIUM
    }

}