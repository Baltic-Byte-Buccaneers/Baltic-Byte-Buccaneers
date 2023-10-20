package com.example.balticbytebuccaneers.util

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addBottomSlideComposable(destination: String, content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit) {
    composable(
        route = destination,
        enterTransition = {
            slideInVertically(
                animationSpec = tween(500),
                initialOffsetY = { it })
        },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = {
            slideOutVertically(
                animationSpec = tween(500),
                targetOffsetY = { it })
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(500),
                targetAlpha = 100f
            )
        },
        content = content
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addSideSlideComposable(destination: String, content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit) {
    composable(
        route = destination,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(500),
                initialOffsetX = { -it })
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = tween(500),
                targetOffsetX = { -it })
        },
        content = content
    )
}