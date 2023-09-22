package com.example.groovyapp

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayScreenTitle() {
        composeTestRule.setContent {
            HomeScreen()
        }
        composeTestRule.onNodeWithText("Playlists").assertIsDisplayed()
    }

    @Test
    fun displayListOfPlaylists() {
        composeTestRule.setContent {
            HomeScreen()
        }
        composeTestRule.onNodeWithTag("playlistLazyColumn").onChildren().assertCountEquals(2)


    }
}