package io.moxd.mocohands_on.model

import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

val Firebase.databaseEU: FirebaseDatabase
    get() = Firebase.database("https://moco-hands-on-default-rtdb.europe-west1.firebasedatabase.app")