package com.example.projectapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projectapp.ui.screens.*
import com.example.projectapp.viewmodels.DataRepository
import com.example.projectapp.viewmodels.DataViewModel

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()


    val viewModel = viewModel<DataViewModel>()

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surface
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                    //label = { Text("Settings") },
                    selected = currentRoute == "settings",
                    onClick = { navController.navigate("settings"){
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    } }
                )

                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    //label = { Text("Home") },
                    selected = currentRoute == "home",
                    onClick = { navController.navigate("home"){
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    } }
                )

                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.List, contentDescription = "Table") },
                    //label = { Text("Table") },
                    selected = currentRoute == "Table",
                    onClick = { navController.navigate("Table"){
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    } }
                )
            }
        }
    ) {innerPadding ->
        NavHost(navController, startDestination = "home", Modifier.padding(innerPadding)) {
            composable("home") { SunScreenState(viewModel) }
            composable("settings") { SettingsScreen(viewModel) }
            composable("Table") { TableScreen(viewModel) }
        }
    }
}