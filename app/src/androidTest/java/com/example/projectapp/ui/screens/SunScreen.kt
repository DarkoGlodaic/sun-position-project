package com.example.projectapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.projectapp.ui.components.BottomNavigationBar
import com.example.projectapp.ui.theme.ProjectAppTheme
import org.junit.Rule
import org.junit.Test

class SunScreen {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun roundUp(){
        composeTestRule.setContent {
            ProjectAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomNavigationBar()
                }
            }
        }
    }
}