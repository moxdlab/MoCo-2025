package io.moxd.mocohands_on.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import io.moxd.mocohands_on.viewmodel.ContactViewModel

@Composable
fun DetailScreen(id: Int, viewModel: ContactViewModel = viewModel()){
    val contact = viewModel.getContact(id)
    Text("Placeholder for details of ${contact?.firstName}")
}