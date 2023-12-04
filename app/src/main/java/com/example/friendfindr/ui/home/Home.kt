package com.example.friendfindr.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.friendfindr.ui.favorites.Favorites
import com.example.friendfindr.ui.personlist.PersonList
import com.example.friendfindr.ui.personlist.PersonListViewModel

//kotlin->class

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {

    val navController= rememberNavController()

    //crear iteraccion con botones
    val bottomNavegationItems=listOf(
        BottomNavigationScreen.PersonList,      //"PersonList" esto yo lo escribo, no es la clase
        BottomNavigationScreen.Favorites,       //"Favorites" esto yo lo escribo, no es la clase
    )

    //esta sera la parte de navegacion en la parte inferior
    Scaffold(
        bottomBar={
            AppBottomNavegation(navController,bottomNavegationItems)
        }
    ) { paddingValues ->

        Main(navController, modifier= Modifier.padding(paddingValues))
    }
}

@Composable
fun AppBottomNavegation(navController: NavHostController, items: List<BottomNavigationScreen>){

    BottomNavigation {

        val navaBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navaBackStackEntry?.destination

        items.forEach{screen->
            BottomNavigationItem(

                selected = currentDestination?.hierarchy?.any{
                    it.route==screen.route
                }==true,
                onClick = { navController.navigate(screen.route) },
                icon = { Icon(screen.icon,null) }
            )
        }
    }
}


@Composable
fun Main(navController: NavHostController, modifier: Modifier){

    //aqui recien llamo al view model
    val viewModel:PersonListViewModel= viewModel()

    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreen.PersonList.route, //inicia mostrando la lista de characters
        modifier
    ){

        composable(BottomNavigationScreen.PersonList.route){
            PersonList(viewModel)  //aca llamamos a la entidad que cree
        }

        composable(BottomNavigationScreen.Favorites.route){
            Favorites(viewModel)  //aca llamamos a la entidad que cree
        }
    }
}

//creamos clase sellada
sealed class BottomNavigationScreen(val route: String, val icon: ImageVector){
    object PersonList: BottomNavigationScreen("Personas", Icons.Filled.Home)
    object Favorites: BottomNavigationScreen("Favoritos", Icons.Filled.Favorite)

}


