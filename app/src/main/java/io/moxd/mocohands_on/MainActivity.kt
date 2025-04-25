package io.moxd.mocohands_on

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.moxd.mocohands_on.model.Contact
import io.moxd.mocohands_on.ui.composables.OurScaffold
import io.moxd.mocohands_on.ui.screens.ContactScreen
import io.moxd.mocohands_on.ui.screens.DetailScreen
import io.moxd.mocohands_on.ui.screens.HomeScreen
import io.moxd.mocohands_on.ui.theme.MoCoHandsOnTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MoCoHandsOnTheme {
                OurScaffold(
                    onNavigate = { route ->
                        navController.navigate(route = route)
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute
                    ) {
                        composable<HomeRoute> {
                            val contacts = listOf(
                                Contact("Hans", "Hansen"),
                                Contact("Peter", "Petersen"),
                            )
                            HomeScreen(
                                contacts,
                                onContactClick = {
                                    navController.navigate(DetailRoute(contacts[it].firstName))
                                }
                            )
                        }
                        composable<DetailRoute> { backStack ->
                            val name = backStack.toRoute<DetailRoute>().name
                            DetailScreen(name)
                        }
                        composable<ContactRoute> {
                            ContactScreen()
                        }
                    }

                }
            }
        }
    }
}

sealed class Route

@Serializable
data object HomeRoute : Route()

@Serializable
data object ContactRoute : Route()

@Serializable
data class DetailRoute(val name: String) : Route()

