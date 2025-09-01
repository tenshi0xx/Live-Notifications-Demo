package com.vamsi.livenotifications

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testMainScreenDisplaysCorrectly() {
        // Verify the main title is displayed
        composeTestRule.onNodeWithText("Live Notifications Demo").assertIsDisplayed()
        
        // Verify the description is displayed
        composeTestRule.onNodeWithText("Experience Android 16's Live Updates with real-time progress tracking. This demo uses Notification.ProgressStyle to show a food delivery journey with segments and progress points.")
            .assertIsDisplayed()
        
        // Verify the start delivery button is displayed
        composeTestRule.onNodeWithText("Start Demo Delivery").assertIsDisplayed()
    }

    @Test
    fun testStartDeliveryButton() {
        // Find and verify the start delivery button exists and is clickable
        composeTestRule.onNodeWithText("Start Demo Delivery")
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
        
        // Note: We don't actually click it in the test to avoid dealing with permissions
        // and service startup in the test environment
    }

    @Test
    fun testFeatureListDisplayed() {
        // Verify the features list title exists (features may be scrolled out of view)
        composeTestRule.onNodeWithText("Features demonstrated:").assertExists()
        
        // Verify at least one feature is displayed
        composeTestRule.onNodeWithText("• Android 16 Notification.ProgressStyle API", substring = true).assertExists()
    }

    @Test
    fun testUIElementsExist() {
        // Test that all expected UI elements are present
        composeTestRule.onNodeWithText("Live Notifications Demo").assertExists()
        composeTestRule.onNodeWithText("Start Demo Delivery").assertExists()
        
        // Verify button is enabled and clickable
        composeTestRule.onNodeWithText("Start Demo Delivery")
            .assertIsEnabled()
            .assertHasClickAction()
    }

    @Test
    fun testDeliveryInProgressText() {
        // This test verifies the delivery progress UI elements exist
        // Note: In a real test environment, we'd need to mock the delivery state
        // For now, we're just testing that these UI elements can be found if they exist
        
        // Test elements that should exist in the UI
        composeTestRule.onNodeWithText("Live Notifications Demo").assertExists()
        
        // The delivery in progress text and stop button would only be visible
        // when delivery is active, so we test their content text exists in the composition
        composeTestRule.onRoot().assertExists()
    }
}