package com.ezadetoro.studyhelper.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ezadetoro.studyhelper.R
import com.ezadetoro.studyhelper.domain.model.Subject
import com.ezadetoro.studyhelper.presentation.components.CountCard
import com.ezadetoro.studyhelper.presentation.components.SubjectCard
import com.ezadetoro.studyhelper.presentation.components.tasksList

@Composable
fun DashboardScreen() {

    val subjects = listOf(
        Subject("Maths", 10f, Subject.subjectCardColors[0]),
        Subject("Physics", 10f, Subject.subjectCardColors[1]),
        Subject("Computer", 10f, Subject.subjectCardColors[2]),
        Subject("Stats", 10f, Subject.subjectCardColors[3]),
        Subject("Fine Arts", 10f, Subject.subjectCardColors[4 % Subject.subjectCardColors.size]),
    )

    Scaffold(
        topBar = { DashboardTopAppbar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CountCardSection(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    subjectCount = 5,
                    studiesHours = "10",
                    goalHours = "12"
                )
            }
            item {
                SubjectCardSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = subjects
                )
            }
            item {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 48.dp, vertical = 20.dp)
                ) {
                    Text(text="Start Study Session")
                }
            }
            tasksList(
                sectionTitle = "Today's Tasks",
                emptyListText = "You do not have any upcoming task.\n " +
                        "Click the + button in subject screen to add new task",
                tasks = emptyList()

            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardTopAppbar(){
    CenterAlignedTopAppBar(
        title = {
            Text(text = "StudyBuddy", style = MaterialTheme.typography.headlineMedium)
        }
    )
}

@Composable
private fun CountCardSection(
    modifier: Modifier,
    subjectCount: Int,
    studiesHours: String,
    goalHours: String
) {
    Row(modifier = modifier) {
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject Count",
            count = "$subjectCount"
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Studied Hours",
            count = studiesHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Study Hours",
            count = goalHours
        )
    }
}

@Composable
private fun SubjectCardSection(
    modifier: Modifier,
    subjectList: List<Subject>,
    emptySubjectText: String = "You do not have any subjects.\n Click the + button to add a subject"
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text= "SUBJECTS",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start=12.dp))
            IconButton(
                onClick = {}
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Subject")
            }
        }
        if(subjectList.isEmpty()){
            Image(
                modifier = Modifier.size(120.dp).align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.img_books),
                contentDescription = "No Subject"
            )
            Text(
                text = emptySubjectText,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Gray

            )

        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ) {
            items(subjectList){ subject ->
                SubjectCard(
                    subjectName = subject.name,
                    gradientColors = subject.color,
                    onclick = {}
                )
            }
        }
    }
}