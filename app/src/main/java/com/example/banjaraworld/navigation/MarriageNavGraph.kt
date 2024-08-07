package com.example.banjaraworld.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.banjaraworld.presentation.marriageregistration.firstscreenmarriage.MarriageRegistrationGenderScreen
import com.example.banjaraworld.presentation.marriageregistration.fourthscreenmarriage.MarriageUploadPhotoScreen
import com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage.MarriageRegistrationUserNameScreen
import com.example.banjaraworld.presentation.marriageregistration.seventh.MarriageHeightScreen
import com.example.banjaraworld.presentation.marriageregistration.sixthmarriagescreen.UserBirthDateAndTime
import com.example.banjaraworld.presentation.marriageregistration.thirdscreenmarriage.MarriageStateAndCityScreen

@RequiresApi(Build.VERSION_CODES.Q)
fun NavGraphBuilder.marriageNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.MARRIAGE,
        startDestination = MarriageScreen.MarriageRegistrationGenderScreen.route
    ) {

        composable(MarriageScreen.MarriageRegistrationNameScreen.route) {
            MarriageRegistrationUserNameScreen(onContinueClick = {
                navController.navigate(MarriageScreen.MarriageRegistrationPhotoUploadScreen.route)
            })
        }
        composable(MarriageScreen.MarriageRegistrationAgeScreen.route) {
//            MarriageAgeScreen()
        }
        composable(MarriageScreen.MarriageRegistrationHeightScreen.route) {
            MarriageHeightScreen(onContinueClick = {
                navController.navigate(MarriageScreen.MarriageRegistrationStateAndCityScreen.route)
            })
        }
        composable(MarriageScreen.MarriageRegistrationStateAndCityScreen.route) {
            MarriageStateAndCityScreen()
        }
        composable(MarriageScreen.MarriageRegistrationPhotoUploadScreen.route) {
            MarriageUploadPhotoScreen(onContinueClick = {
                navController.navigate(MarriageScreen.MarriageUserBirthDateAndTimeScreen.route)
            })
        }
        composable(MarriageScreen.MarriageUserBirthDateAndTimeScreen.route) {
            UserBirthDateAndTime(onContiueClick = {
                navController.navigate(MarriageScreen.MarriageRegistrationHeightScreen.route)
            })
        }
        composable(MarriageScreen.MarriageRegistrationGenderScreen.route) {
            MarriageRegistrationGenderScreen(
                onContinue = {
                    navController.navigate(MarriageScreen.MarriageRegistrationNameScreen.route)
                }
            )
        }

    }
}

sealed class MarriageScreen(val route: String) {

    object Marriage : MarriageScreen("marriage_screen")
    object MarriageRegistrationNameScreen : MarriageScreen("marriage_registration_screen")
    object MarriageRegistrationAgeScreen : MarriageScreen("marriage_registration_age_screen")
    object MarriageRegistrationHeightScreen : MarriageScreen("marriage_registration_height_screen")
    object MarriageRegistrationStateAndCityScreen :
        MarriageScreen("marriage_registration_state_and_city_screen")

    object MarriageRegistrationMaritalStatusScreen :
        MarriageScreen("marriage_registration_marital_status_screen")

    object MarriageRegistrationEducationScreen :
        MarriageScreen("marriage_registration_education_screen")

    object MarriageRegistrationOccupationScreen :
        MarriageScreen("marriage_registration_occupation_screen")

    object MarriageRegistrationIncomeScreen : MarriageScreen("marriage_registration_income_screen")
    object MarriageRegistrationPhotoUploadScreen :
        MarriageScreen("marriage_registration_photo_upload_screen")

    object MarriageStateAndCityScreen : MarriageScreen("marriage_state_and_city_screen")

    object MarriageUserBirthDateAndTimeScreen :
        MarriageScreen("marriage_user_birth_date_and_time_screen")

    object MarriageRegistrationGenderScreen : MarriageScreen("marriage_registration_gender_screen")

}
