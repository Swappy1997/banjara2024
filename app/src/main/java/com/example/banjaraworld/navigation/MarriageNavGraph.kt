
package com.example.banjaraworld.navigation

import MarriageHomeScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.banjaraworld.presentation.commonwidgets.MarriageSheet
import com.example.banjaraworld.presentation.marriageregistration.fifthscreenmarraige.MarriageOccupationAndEducationScreen
import com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage.MarriageRegistrationGenderScreen
import com.example.banjaraworld.presentation.marriageregistration.fourthscreenmarriage.MarriageUploadPhotoScreen
import com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage.MarriageRegistrationUserNameScreen
import com.example.banjaraworld.presentation.marriageregistration.seventh.MarriageHeightScreen
import com.example.banjaraworld.presentation.marriageregistration.sixthmarriagescreen.UserBirthDateAndTime
import com.example.banjaraworld.presentation.marriageregistration.thirdscreenmarriage.MarriageStateAndCityScreen

@RequiresApi(Build.VERSION_CODES.Q)
fun NavGraphBuilder.marriageNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.MARRIAGE, startDestination = MarriageScreen.M_R_First_Screen.route
    ) {

        composable(MarriageScreen.Marriage.route) {
            MarriageSheet {
                navController.navigate(MarriageScreen.M_R_First_Screen.route)
            }

        }
        composable(MarriageScreen.M_R_First_Screen.route) {
            MarriageRegistrationGenderScreen(onContinue = {
                navController.navigate(MarriageScreen.M_R_Second_Screen.route)
            })
        }

        composable(MarriageScreen.M_R_Second_Screen.route) {
            MarriageRegistrationUserNameScreen(onContinueClick = {
                navController.navigate(MarriageScreen.M_R_Third_Screen.route)
            })
        }
        composable(MarriageScreen.M_R_Third_Screen.route) {
            UserBirthDateAndTime(onContiueClick = {
                navController.navigate(MarriageScreen.M_R_Fourth_Screen.route)
            })
        }
        composable(MarriageScreen.M_R_Fourth_Screen.route) {
            MarriageHeightScreen(onContinueClick = {
                navController.navigate(MarriageScreen.M_R_Fifth_Screen.route)
            })
        }
        composable(MarriageScreen.M_R_Fifth_Screen.route) {
            MarriageStateAndCityScreen(onContinueClick = {
                navController.navigate(MarriageScreen.M_R_Sixth_Screen.route)
            })
        }
        composable(MarriageScreen.M_R_Sixth_Screen.route) {
            MarriageUploadPhotoScreen(onContinueClick = {
                navController.navigate(MarriageScreen.M_R_Seventh_Screen.route)
            })
        }
        composable(MarriageScreen.M_R_Seventh_Screen.route) {
            MarriageOccupationAndEducationScreen(onContinueClick = {
                navController.navigate(MarriageScreen.MARRIAGE_HOME_SCREEN.route)
            })
        }
        composable(MarriageScreen.MARRIAGE_HOME_SCREEN.route) {
            MarriageHomeScreen()
        }
    }
}

sealed class MarriageScreen(val route: String) {

    object Marriage : MarriageScreen("marriage_screen")
    object M_R_First_Screen : MarriageScreen("m_r_gender_screen")
    object M_R_Second_Screen : MarriageScreen("m_r_screen")
    object M_R_Third_Screen : MarriageScreen("m_r_birth_screen")
    object M_R_Fourth_Screen : MarriageScreen("m_r_height_screen")
    object M_R_Fifth_Screen : MarriageScreen("m_r_state_and_city_screen")
    object M_R_Sixth_Screen : MarriageScreen("m_r_photo_upload_screen")
    object M_R_Seventh_Screen : MarriageScreen("m_r_occupation_and_education_screen")
object MARRIAGE_HOME_SCREEN : MarriageScreen("marriage_home_screen")
}
