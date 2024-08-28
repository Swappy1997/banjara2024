package com.example.banjaraworld.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.banjaraworld.navigation.ShoppingScreens
import com.example.banjaraworld.presentation.shopping.ShoppingDetails

class ShoppingDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val link = intent?.data?.toString()
        setContent {
//            ShoppingDetails(link = link, onclick = {
//            }, onAddtoBagClick = {
//                navController.navigate(ShoppingScreens.ShoppingCart.route)
//            })
        }
    }
}