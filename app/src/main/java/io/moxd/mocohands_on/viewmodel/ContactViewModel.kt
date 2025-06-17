package io.moxd.mocohands_on.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import io.moxd.mocohands_on.model.Contact
import io.moxd.mocohands_on.model.ContactStore
import io.moxd.mocohands_on.model.FakeContactStore
import io.moxd.mocohands_on.model.databaseEU
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

class ContactViewModel : ViewModel() {

    private val rtdb = Firebase.databaseEU
    private val contacts = rtdb.getReference("contacts")

    private var contactsList: MutableStateFlow<List<Contact>> = MutableStateFlow(emptyList())

    init {
        contacts.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                contactsList.value = snapshot.children.mapNotNull {
                    it.getValue<Contact>()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("ContactViewModel", "Failed to read value.", error.toException())
            }

        })
    }

    private val contactStore: ContactStore = FakeContactStore
    fun getContacts() = contactsList

    fun addContact(firstName: String, lastName: String) {
        val contact = Contact(UUID.randomUUID().hashCode(), firstName, lastName)
        contacts.push().setValue(contact)
    }

    fun getContact(id: Int) = contactStore.getContact(id)
}

