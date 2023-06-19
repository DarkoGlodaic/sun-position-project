package com.example.projectapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import com.example.projectapp.viewmodels.DataViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(viewModel: DataViewModel){
    var checkedDark by remember { mutableStateOf(false) }

    val settingCalc by viewModel.settingCalculation.collectAsState()

    val icon: (@Composable () -> Unit)? = if (checkedDark) {
        {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }
    val icon2: (@Composable () -> Unit)? = if (settingCalc) {
        {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }

    Scaffold(
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
        topBar = { TopAppBar(
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surface
    ) {
        Text(
            text = "Settings",
            fontSize = 25.sp,
            modifier = Modifier.padding(8.dp),
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface)
    }
    }) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start) {
            Row(modifier = Modifier.height(50.dp)) {
                Text(
                    text = "Dark mode",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.padding(100.dp))
                Column(modifier = Modifier
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End) {
                    Switch(
                        modifier = Modifier.semantics { contentDescription = "Dark mode" },
                        checked = checkedDark,
                        thumbContent = icon,
                        onCheckedChange = { checkedDark = it})
                }
            }
            Divider(thickness = Dp.Hairline, modifier = Modifier.padding(8.dp))

            Row(modifier = Modifier.height(50.dp)) {
                Text(
                    text = "Local calculation",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.padding(73.dp))
                Column(modifier = Modifier
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End) {
                        Switch(
                            modifier = Modifier.semantics { contentDescription = "local calculation" },
                            checked = settingCalc,
                            thumbContent = icon2,
                            onCheckedChange = {
                            viewModel.brukKalkulering(it)
                            })
                }
            }
        }
    }
}