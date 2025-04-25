package io.moxd.mocohands_on

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.moxd.mocohands_on.model.Contact
import io.moxd.mocohands_on.ui.composables.OurScaffold
import io.moxd.mocohands_on.ui.screens.HomeScreen
import io.moxd.mocohands_on.ui.theme.MoCoHandsOnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoCoHandsOnTheme {
                OurScaffold {
                    HomeScreen(
                        listOf(
                            Contact("Hans", "Hansen"),
                            Contact("Peter", "Petersen"),
                        )
                    )
                }
            }
        }
    }
}

