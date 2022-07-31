package com.example.jetpackdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userViewModel = ViewModelProvider(this,
            MainViewModelFactory(application)).get(UserViewModel::class.java)
        setContent {
            MainScreenView(userViewModel)
//            JetpackDemoTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
//            }

        }
    }

}

@Composable
fun MainScreenView(userViewModel: UserViewModel){

    val navController = rememberNavController()
    val commentsAlpha = if ( navController.currentDestination?.route == "add_post") 1f else 0f

    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = { },
//                modifier = Modifier.alpha(commentsAlpha)){
//                Icon(imageVector = Icons.Rounded.Add, contentDescription = "fab")
//            }
//        },
//        floatingActionButtonPosition = FabPosition.Center,
//        isFloatingActionButtonDocked = true,
        bottomBar = { BottomNavigation(navController = navController)},

    ) {

        NavigationGraph(navController = navController,userViewModel = userViewModel)
    }

}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.User,
    )
    androidx.compose.material.BottomNavigation(backgroundColor = Color.White,
        elevation = 1.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach{ item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title,
                    fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.6f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }


    }
}

@Composable
fun NavigationGraph(navController: NavHostController,userViewModel: UserViewModel){
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.User.screen_route) {
            AddUserScreen(userViewModel)
        }

    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    val userViewModel = ViewModelProvider(LocalContext).get(UserViewModel::class.java)
//    JetpackDemoTheme {
//        MainScreenView()
//    }
//}


