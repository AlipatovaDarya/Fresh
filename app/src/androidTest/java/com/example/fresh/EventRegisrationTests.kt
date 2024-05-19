package com.example.fresh

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class EventRegisrationTests {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    @Test
    fun testEventRegistration() {
        composeTestRule.apply {
            onNodeWithText("Вход")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Email")
                .assertIsDisplayed()
                .performTextInput("webshore@yandex.ru")
            onNodeWithText("Пароль")
                .assertIsDisplayed()
                .performTextInput("123456")
            onNodeWithText("Войти")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Мероприятия")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Предстоящие")
                .assertIsDisplayed().performClick()
            waitForIdle()
            onNodeWithText("регистрация").assertIsDisplayed().performClick()
            waitForIdle()
            onNodeWithText("Зарегистрироваться").assertIsDisplayed().performClick()
            waitForIdle()
            onNodeWithText("вы зарегистрированы").assertIsDisplayed()
        }
    }


    @Test
    fun testCancelEventRegistration() {
        composeTestRule.apply {
            onNodeWithText("Вход")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Email")
                .assertIsDisplayed()
                .performTextInput("webshore@yandex.ru")
            onNodeWithText("Пароль")
                .assertIsDisplayed()
                .performTextInput("123456")
            onNodeWithText("Войти")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Мероприятия")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Предстоящие")
                .assertIsDisplayed().performClick()
            waitForIdle()
            onNodeWithText("регистрация").assertIsDisplayed().performClick()
            waitForIdle()
            onNodeWithText("Отмена").assertIsDisplayed().performClick()
            waitForIdle()
            onNodeWithText("вы регистрация").assertIsDisplayed()

        }
    }

}