package com.example.jetpackdemo

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home",R.drawable.home,"Home")
    object User: BottomNavItem("Users",R.drawable.user,"Users")
}