package io.moxd.mocohands_on

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.moxd.mocohands_on.ui.composables.OurScaffold
import io.moxd.mocohands_on.ui.screens.ContactScreen
import io.moxd.mocohands_on.ui.screens.DetailScreen
import io.moxd.mocohands_on.ui.screens.HomeScreen
import io.moxd.mocohands_on.ui.theme.MoCoHandsOnTheme
import io.moxd.mocohands_on.viewmodel.ContactViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val contactViewModel = viewModel<ContactViewModel>()
            MoCoHandsOnTheme {
                OurScaffold(
                    onNavigate = { route ->
                        navController.navigate(route = route)
                    },
                    fapClick = {
                        contactViewModel.addContact("Max", "Musterfrau")
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute
                    ) {
                        composable<HomeRoute> {
                            HomeScreen(
                                onContactClick = { id ->
                                    navController.navigate(DetailRoute(id))
                                }
                            )
                        }
                        composable<DetailRoute> { backStack ->
                            val id = backStack.toRoute<DetailRoute>().id
                            DetailScreen(id)
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
data class DetailRoute(val id: Int) : Route()
