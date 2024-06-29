package com.example.engaz.features.profile.view.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@Composable
fun LogoutAlertDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "تسجيل الخروج", fontSize = 20.sp) },
        text = { Text(text = "هل انت متأكد من تسجيل الخروج ؟") },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("خروج")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("الغاء")
            }
        },
        properties = DialogProperties(dismissOnClickOutside = false)
    )
}