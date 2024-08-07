package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDialog(
    modifier: Modifier = Modifier,
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val dateState = rememberDatePickerState()
    val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    var showDialog by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { onDismiss() },
                confirmButton = {
                    Button(
                        onClick = {
                            dateState.selectedDateMillis?.let {
                                onDateSelected(dateFormatter.format(Date(it)))
                            }
                            showDialog = false
                        }
                    ) {
                        Text(text = "OK")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { onDismiss() }
                    ) {
                        Text(text = "Cancel")
                    }
                },
            ) {
                DatePicker(
                    state = dateState,
                    showModeToggle = true,
                )

            }
        }
    }
}
