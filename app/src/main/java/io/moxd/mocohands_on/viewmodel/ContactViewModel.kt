package io.moxd.mocohands_on.viewmodel

import androidx.lifecycle.ViewModel
import io.moxd.mocohands_on.model.ContactStore
import io.moxd.mocohands_on.model.FakeContactStore

class ContactViewModel: ViewModel() {

    private val contactStore: ContactStore = FakeContactStore
    fun getContacts() = contactStore.getAllContacts()

    fun addContact(firstName: String, lastName: String) {
        contactStore.addContact(firstName, lastName)
    }

    fun getContact(id: Int) = contactStore.getContact(id)
}