package com.example.groovyapp

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class PlaylistFeature {


    @get:Rule(order = 0)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.activity.setContent {
            HomeScreen(homeViewModel = hiltViewModel())
        }
    }

    @Test
    fun displayScreenTitle() {

        composeTestRule.onNodeWithText("Playlists").assertIsDisplayed()
    }

    @Test
    fun displayListOfPlaylists() {

        Thread.sleep(3000)
        composeTestRule.onNodeWithTag("playlistLazyColumn").onChildren().assertCountEquals(15)
        composeTestRule.onNodeWithTag("playlistItem1").assertTextContains("Hard Rock Cafe")
        composeTestRule.onNodeWithTag("playlistSubItem1").assertTextContains("rock")
        composeTestRule.onAllNodesWithContentDescription("listOfImage1")
            .onFirst()
            .assertIsDisplayed()
    }

    @Test
    fun hidesLoader() {
        Thread.sleep(4000)
        composeTestRule.onNodeWithTag("loader").assertDoesNotExist()
    }
}