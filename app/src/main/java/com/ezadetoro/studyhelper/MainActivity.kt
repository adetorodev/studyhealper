package com.ezadetoro.studyhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ezadetoro.studyhelper.presentation.subject.SubjectScreen
import com.ezadetoro.studyhelper.presentation.theme.StudyHelperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyHelperTheme {
                SubjectScreen()
            }
        }
    }
}

