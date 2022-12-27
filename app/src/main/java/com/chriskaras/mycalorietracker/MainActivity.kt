package com.chriskaras.mycalorietracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chriskaras.mycalorietracker.features.onboarding.composables.activitylevel.ActivityLevelScreen
import com.chriskaras.mycalorietracker.features.onboarding.composables.age.AgeScreen
import com.chriskaras.mycalorietracker.features.onboarding.composables.gender.GenderScreen
import com.chriskaras.mycalorietracker.features.onboarding.composables.goal.GoalScreen
import com.chriskaras.mycalorietracker.features.onboarding.composables.height.HeightScreen
import com.chriskaras.mycalorietracker.features.onboarding.composables.nutrientgoal.NutrientGoalScreen
import com.chriskaras.mycalorietracker.features.onboarding.composables.weight.WeightScreen
import com.chriskaras.mycalorietracker.util.navigate.Route
import com.chriskaras.mycalorietracker.ui.theme.MyCalorieTrackerTheme
import com.chriskaras.mycalorietracker.features.onboarding.composables.welcome.WelcomeScreen
import com.chriskaras.mycalorietracker.util.navigate.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCalorieTrackerTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {SnackbarHost(snackbarHostState)}
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.NUTRIENT_GOAL){

                        composable(Route.WELCOME){
                            WelcomeScreen(onNavigate = navController::navigate)
                        }

                        composable(Route.GENDER){
                            GenderScreen(onNavigate = navController::navigate)
                        }

                        composable(Route.AGE){
                            AgeScreen(onNavigate = navController::navigate, snackbarHostState = snackbarHostState)
                        }
                        composable(Route.HEIGHT){
                            HeightScreen(
                                snackbarHostState = snackbarHostState,
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Route.WEIGHT){
                            WeightScreen(
                                snackbarHostState = snackbarHostState,
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Route.ACTIVITY){
                            ActivityLevelScreen(
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Route.GOAL){
                            GoalScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.NUTRIENT_GOAL){
                            NutrientGoalScreen(
                                snackbarHostState = snackbarHostState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW){

                        }
                    }
                }
            }
        }
    }
}

