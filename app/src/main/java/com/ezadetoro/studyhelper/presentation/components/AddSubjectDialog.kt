package com.ezadetoro.studyhelper.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ezadetoro.studyhelper.domain.model.Subject

@Composable
fun AddSubjectDialog(
    isOpen: Boolean,
    title: String = "Add/Update Subject",
    selectedColors: List<Color>,
    onColoChange: (List<Color>) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    subjectName: String,
    goalHours: String,
    onSubjectNameChange: (String) -> Unit,
    onGoalHoursChange: (String) -> Unit
) {

    var goalHourError by rememberSaveable { mutableStateOf<String?>(null) }
    var subjectNamesError by rememberSaveable { mutableStateOf<String?>(null) }

    subjectNamesError = when{
        subjectName.isBlank() -> "Please enter subject name"
        subjectName.length < 2 -> "Subject name is too short"
        subjectName.length > 20 -> "Subject name is too long"

        else -> null
    }

    goalHourError = when{
        goalHours.isBlank() -> "Please enter study goal hour"
        goalHours.toFloatOrNull() == null -> "Please enter a valid number"
        goalHours.toFloat() < 1f -> "Please set at least 1 hour"
        goalHours.toFloat() > 1000f -> "Please set a maximum of 1000 hours"

        else -> null
    }

    if (isOpen) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text= title) },
            text = {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Subject.subjectCardColors.forEach { colors ->
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .border(width = 1.dp,
                                        color = if (colors == selectedColors) Color.Black
                                        else Color.Transparent,
                                        shape = CircleShape)
                                    .background(brush = Brush.verticalGradient(colors))
                                    .clickable { onColoChange(colors) }
                            )
                        }
                    }
                    OutlinedTextField(
                        value = subjectName,
                        onValueChange = onSubjectNameChange,
                        label = { Text(text="Subject Name")},
                        singleLine  = true,
                        isError = subjectNamesError != null && subjectName.isNotBlank(),
                        supportingText = {Text(text = subjectNamesError.orEmpty())}
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = goalHours,
                        onValueChange = onGoalHoursChange,
                        label = { Text(text="Goal Study Hours")},
                        singleLine  = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = goalHourError != null && goalHours.isNotBlank(),
                        supportingText = {Text(text = goalHourError.orEmpty())}
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirmButtonClick,
                    enabled = subjectNamesError == null && goalHourError == null
                ) {
                    Text(text= "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest ) {
                    Text(text="Cancel")
                }
            }
        )
    }


}