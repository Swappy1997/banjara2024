package com.example.banjaraworld.presentation.commonwidgets

import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.banjaraworld.common.utils.BwDimensions
import java.text.SimpleDateFormat
import java.util.*

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
                properties = DialogProperties(usePlatformDefaultWidth = false),
                shape = DatePickerDefaults.shape,
                modifier = Modifier.width(200.dp),
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
