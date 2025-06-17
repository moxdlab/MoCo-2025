package io.moxd.mocohands_on.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.moxd.mocohands_on.model.Contact
import io.moxd.mocohands_on.ui.composables.ContactCard
import io.moxd.mocohands_on.ui.composables.OurScaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import io.moxd.mocohands_on.viewmodel.ContactViewModel


@Composable
fun HomeScreen(viewModel: ContactViewModel = viewModel(), onContactClick: (Int) -> Unit) {
    val contacts by viewModel.getContacts().collectAsStateWithLifecycle(emptyList())
    HomeScreen(contacts, onContactClick)
}

@Composable
fun HomeScreen(contacts: List<Contact>, onContactClick: (Int) -> Unit = {}) {
    LazyColumn {
        items(contacts) { contact ->
            ContactCard(
                contact.firstName,
                contact.lastName,
                callClick = { onContactClick(contact.id) })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        listOf(
            Contact(0, "Hans", "Hansen"),
            Contact(1, "Peter", "Petersen"),
        )
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenScaffoldPreview() {
    OurScaffold {
        HomeScreen(
            listOf(
                Contact(0, "Hans", "Hansen"),
                Contact(1, "Peter", "Petersen"),
            )
        )
    }
}