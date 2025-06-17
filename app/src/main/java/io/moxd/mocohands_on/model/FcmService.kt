package io.moxd.mocohands_on.model

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FcmService : FirebaseMessagingService() {

    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())
    private val TAG = this.javaClass.simpleName

    override fun onMessageReceived(message: RemoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${message.from}")

        // Check if message contains a data payload.
        if (message.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${message.data}")

            //TODO: Check if data needs to be processed by long running job (> 10 s?)
            //...
        }
    }

    override fun onNewToken(token: String) {
        Log.d("FcmService", "Refreshed token: $token")
        serviceScope.launch {
            sendRegistrationToServer(token)
        }
        super.onNewToken(token)
    }

    private suspend fun sendRegistrationToServer(token: String) {
        HttpClient(Android).post("https://cuddly-mangos-end.loca.lt/token") {
            contentType(ContentType.Text.Plain)
            setBody(token)
        }
    }

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }
}