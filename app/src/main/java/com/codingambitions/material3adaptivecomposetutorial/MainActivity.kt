package com.codingambitions.material3adaptivecomposetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowWidthSizeClass
import com.codingambitions.material3adaptivecomposetutorial.ui.theme.Material3AdaptiveComposeTutorialTheme

data class TodoItem(val title: String)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Material3AdaptiveComposeTutorialTheme {

                //ListDetailPaneScaffoldExample()
                //NavigableListDetailPaneScaffoldExample()
                SupportingPaneScaffoldExample()

            }
        }
    }


}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun ListDetailPaneScaffoldExample() {
    val navigator = rememberListDetailPaneScaffoldNavigator<TodoItem>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        modifier = Modifier.statusBarsPadding(),
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            //AnimatedPane {
            MyList(
                onItemClick = { item ->
                    navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item)
                }
            )
            //}
        },
        detailPane = {
            navigator.currentDestination?.content?.let {
                //AnimatedPane {
                MyDetails(
                    item = it,
                    onItemClick = { item ->
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Extra, item)
                    }
                )
                //}
            } ?: NothingSelectedUi()

        },
        extraPane = {
            navigator.currentDestination?.content?.let {
                ExtraPane(it)
            } ?: NothingSelectedUi()

        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun SupportingPaneScaffoldExample() {
    val navigator = rememberSupportingPaneScaffoldNavigator<TodoItem>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    SupportingPaneScaffold(
        modifier = Modifier.statusBarsPadding(),
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = {
            //AnimatedPane {
            MyList(
                onItemClick = { item ->
                    navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item)
                }
            )
            //}
        },
        supportingPane = {
            navigator.currentDestination?.content?.let {
                //AnimatedPane {
                MyDetails(
                    item = it,
                    onItemClick = { item ->
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Extra, item)
                    }
                )
                //}
            } ?: NothingSelectedUi()

        },
        extraPane = {
            navigator.currentDestination?.content?.let {
                ExtraPane(it)
            } ?: NothingSelectedUi()

        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun NavigableListDetailPaneScaffoldExample() {
    val navigator = rememberListDetailPaneScaffoldNavigator<TodoItem>()

    NavigableListDetailPaneScaffold(
        navigator = navigator as ThreePaneScaffoldNavigator<Any>,
        modifier = Modifier.statusBarsPadding(),
        listPane = {
            //AnimatedPane {
            MyList(
                onItemClick = { item ->
                    navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item)
                }
            )
            //}
        },
        detailPane = {
            navigator.currentDestination?.content?.let {
                //AnimatedPane {
                MyDetails(
                    item = it,
                    onItemClick = { item ->
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Extra, item)
                    }
                )
                //}
            } ?: NothingSelectedUi()

        },
        extraPane = {
            navigator.currentDestination?.content?.let {
                ExtraPane(it)
            } ?: NothingSelectedUi()
        }
    )
}


@Composable
fun MyList(
    onItemClick: (TodoItem) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        LazyColumn {
            testData.forEach { item ->
                item {
                    ListItem(
                        modifier = Modifier
                            .clickable {
                                onItemClick(item)
                            },
                        headlineContent = {
                            Text(
                                text = item.title,
                            )
                        },
                    )
                }
            }
        }
    }

}

@Composable
fun MyDetails(
    item: TodoItem,
    onItemClick: (TodoItem) -> Unit
) {
    val text = item.title
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(16.dp)
            .clickable {
                onItemClick(item)
            }
    ) {
        Text(
            text = "Title: $text",
            fontSize = 24.sp,
        )
        Spacer(Modifier.size(16.dp))
        Text(
            text = "Add TODO details"
        )
    }
}

@Composable
fun NothingSelectedUi() {

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(16.dp)
    ) { Text(text = "Nothing selected, Please select an Item") }
}

@Composable
fun ExtraPane(item: TodoItem) {

    Card {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(16.dp)
        ) {
            Text(text = item.title)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Material3AdaptiveComposeTutorialTheme {
        //Greeting("Android")
    }
}

val testData = listOf<TodoItem>(
    TodoItem("Todo 1"),
    TodoItem("Todo 2"),
    TodoItem("Todo 3"),
    TodoItem("Todo 4"),
    TodoItem("Todo 5"),
    TodoItem("Todo 6"),
    TodoItem("Todo 7"),
    TodoItem("Todo 8"),
    TodoItem("Todo 9"),
    TodoItem("Todo 10"),
    TodoItem("Todo 11"),
    TodoItem("Todo 12"),
    TodoItem("Todo 13"),
    TodoItem("Todo 14"),
    TodoItem("Todo 15"),
    TodoItem("Todo 16"),
    TodoItem("Todo 17"),
    TodoItem("Todo 18"),
    TodoItem("Todo 19"),
    TodoItem("Todo 20"),
    TodoItem("Todo 21"),
    TodoItem("Todo 22"),
)