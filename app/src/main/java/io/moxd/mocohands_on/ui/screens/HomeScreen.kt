package io.moxd.mocohands_on.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.moxd.mocohands_on.model.Contact
import io.moxd.mocohands_on.ui.composables.ContactCard
import io.moxd.mocohands_on.ui.composables.OurScaffold

@Composable
fun HomeScreen(contacts: List<Contact>, onContactClick: (Int) -> Unit = {}) {
    LazyColumn {
        itemsIndexed(contacts) { index, contact ->
            ContactCard(contact.firstName, contact.lastName, callClick = { onContactClick(index) })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        listOf(
            Contact("Hans", "Hansen"),
            Contact("Peter", "Petersen"),
        )
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenScaffoldPreview() {
    OurScaffold {
        HomeScreen(
            listOf(
                Contact("Hans", "Hansen"),
                Contact("Peter", "Petersen"),
            )
        )
    }
}