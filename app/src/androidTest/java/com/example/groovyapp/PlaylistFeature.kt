package com.example.groovyapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
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
        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("sdfsdfsdf").fetchSemanticsNodes().isNotEmpty()
            composeTestRule.onAllNodesWithTag("playlistLazyColumn").fetchSemanticsNodes()
                .isNotEmpty()

        }

        /*Thread.sleep(4000)
        composeTestRule.onNodeWithTag("playlistLazyColumn").onChildren().assertCountEquals(15)
        composeTestRule.onNodeWithTag("playlistLazyColumn").onChildren().onFirst().assertIsDisplayed()


         */


    }
}