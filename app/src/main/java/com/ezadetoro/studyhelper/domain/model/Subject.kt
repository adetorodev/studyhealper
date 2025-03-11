package com.ezadetoro.studyhelper.domain.model

import androidx.compose.ui.graphics.Color
import com.ezadetoro.studyhelper.presentation.theme.gradient1
import com.ezadetoro.studyhelper.presentation.theme.gradient2
import com.ezadetoro.studyhelper.presentation.theme.gradient3
import com.ezadetoro.studyhelper.presentation.theme.gradient4
import com.ezadetoro.studyhelper.presentation.theme.gradient5

data class Subject(
    val name: String,
    val goalHour: Float,
    val color: List<Color>,
    val subjectId: Int

){
    companion object {
        val subjectCardColors = listOf(gradient1, gradient2, gradient3, gradient4, gradient5)
    }
}