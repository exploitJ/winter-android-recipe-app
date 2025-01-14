package com.surivalcoding.composerecipeapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.surivalcoding.composerecipeapp.presentation.component.BigButton
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class BigButtonKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `test_BigButton`() {
        var isClicked = false

        composeTestRule.setContent {
            BigButton(
                text = "Big button",
                onClick = {
                    isClicked = true
                }
            )
        }

        composeTestRule.onNodeWithText("Big button").performClick()

        assertTrue(isClicked)
    }
}