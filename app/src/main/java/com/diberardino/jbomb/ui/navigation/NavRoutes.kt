package com.diberardino.jbomb.ui.navigation

sealed class NavRoutes(val route: String) {
    object MatchScreen : NavRoutes("matchScreen")
}