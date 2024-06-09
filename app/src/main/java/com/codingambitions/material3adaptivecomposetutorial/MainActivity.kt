package com.codingambitions.material3adaptivecomposetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.core.layout.WindowWidthSizeClass
import com.codingambitions.material3adaptivecomposetutorial.ui.theme.Material3AdaptiveComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Material3AdaptiveComposeTutorialTheme {

                var currentDestination by remember {
                    mutableStateOf(AppDestinations.HOME)
                }

                val adaptiveInfo = currentWindowAdaptiveInfo()
                val layoutType = with(adaptiveInfo) {
                    if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
                        NavigationSuiteType.NavigationDrawer
                    } else {
                        NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
                    }
                }

                NavigationSuiteScaffold(
                    layoutType = layoutType,
                    navigationSuiteItems = {

                        AppDestinations.entries.forEach {

                            item(
                                selected = currentDestination == it,
                                onClick = {
                                    currentDestination = it
                                },
                                icon = {
                                    Icon(
                                        imageVector = it.icon,
                                        contentDescription = it.contentDescription.toString()
                                    )
                                },
                                label = {
                                    Text(text = stringResource(id = it.label))
                                },
                            )
                        }
                    },
                    navigationSuiteColors = NavigationSuiteDefaults.colors(
                        navigationBarContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                        navigationDrawerContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                        navigationRailContainerColor = MaterialTheme.colorScheme.surfaceContainer
                    ),
                ) {
                    when (currentDestination) {
                        AppDestinations.HOME -> HomeDestination()
                        AppDestinations.EXPLORE -> ExploreDestination()
                        AppDestinations.CART -> CartDestination()
                        AppDestinations.WISHLIST -> WishlistDestination()
                        AppDestinations.PROFILE -> ProfileDestination()
                    }
                }

            }
        }
    }
}

@Composable
fun HomeDestination() {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Home")
        }
    }
}

@Composable
fun ExploreDestination() {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Explore")
        }
    }

}

@Composable
fun CartDestination() {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Cart")
        }
    }
}

@Composable
fun ProfileDestination() {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Profile")
        }
    }
}

@Composable
fun WishlistDestination() {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Wishlist")
        }
    }
}


enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    HOME(R.string.home, Icons.Default.Home, R.string.home),
    EXPLORE(R.string.explore, Icons.Default.Search, R.string.explore),
    CART(R.string.cart, Icons.Default.ShoppingCart, R.string.cart),
    WISHLIST(R.string.wishlist, Icons.Default.Favorite, R.string.wishlist),
    PROFILE(R.string.profile, Icons.Default.AccountBox, R.string.profile),
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Material3AdaptiveComposeTutorialTheme {
        //Greeting("Android")
    }
}