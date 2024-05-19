package com.example.fresh

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class GeneralTests {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testCorrectRegistration() {
        composeTestRule.apply {
            onNodeWithText("Регистрация")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Email")
                .assertIsDisplayed()
                .performTextInput("alipatovadg@yandex.ru")
            onNodeWithText("Имя пользователя")
                .assertIsDisplayed()
                .performTextInput("Darya")
            onNodeWithText("Фамилия")
                .assertIsDisplayed()
                .performTextInput("Alipatova")
            onNodeWithText("Пароль")
                .assertIsDisplayed()
                .performTextInput("123456")
            onNodeWithText("Подтвердите пароль")
                .assertIsDisplayed()
                .performTextInput("123456")
            onNodeWithText("Зарегистрироваться")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Заполните корректно все поля").assertDoesNotExist()
        }
    }

    @Test
    fun testIncorrectPasswComf() {
        composeTestRule.apply {
            onNodeWithText("Регистрация")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Email")
                .assertIsDisplayed()
                .performTextInput("alipatovadg@yandex.ru")
            onNodeWithText("Имя пользователя")
                .assertIsDisplayed()
                .performTextInput("Darya")
            onNodeWithText("Фамилия")
                .assertIsDisplayed()
                .performTextInput("Alipatova")
            onNodeWithText("Пароль")
                .assertIsDisplayed()
                .performTextInput("123456")
            onNodeWithText("Подтвердите пароль")
                .assertIsDisplayed()
                .performTextInput("1234")
            onNodeWithText("Зарегистрироваться")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Заполните корректно все поля").assertIsDisplayed()
        }
    }

    @Test
    fun testIncorrectInputRegistration() {
        composeTestRule.apply {
            onNodeWithText("Регистрация")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Email")
                .assertIsDisplayed()
                .performTextInput("alipatovadg@yandex.ru")
            onNodeWithText("Имя пользователя")
                .assertIsDisplayed()
                .performTextInput("Darya")
            onNodeWithText("Фамилия")
                .assertIsDisplayed()
                .performTextInput("")
            onNodeWithText("Пароль")
                .assertIsDisplayed()
                .performTextInput("123456")
            onNodeWithText("Подтвердите пароль")
                .assertIsDisplayed()
                .performTextInput("123456")
            onNodeWithText("Зарегистрироваться")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Заполните корректно все поля").assertIsDisplayed()
        }
    }

    @Test
    fun testIncorrectInputEmail() {
        composeTestRule.apply {
            onNodeWithText("Регистрация")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Email")
                .assertIsDisplayed()
                .performTextInput("alipatovadgyandex.ru")
            onNodeWithText("Имя пользователя")
                .assertIsDisplayed()
                .performTextInput("Darya")
            onNodeWithText("Фамилия")
                .assertIsDisplayed()
                .performTextInput("Alipatova")
            onNodeWithText("Пароль")
                .assertIsDisplayed()
                .performTextInput("123456")
            onNodeWithText("Подтвердите пароль")
                .assertIsDisplayed()
                .performTextInput("123456")
            onNodeWithText("Зарегистрироваться")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Заполните корректно все поля").assertIsDisplayed()
        }
    }

    @Test
    fun testEntrance() {
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
            onNodeWithText("Неверный логин или пароль").assertDoesNotExist()
        }
    }

    @Test
    fun testIncorrectEntrance() {
        composeTestRule.apply {
            onNodeWithText("Вход")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Email")
                .assertIsDisplayed()
                .performTextInput("")
            onNodeWithText("Пароль")
                .assertIsDisplayed()
                .performTextInput("123456")
            onNodeWithText("Войти")
                .assertIsDisplayed()
                .performClick()
            onNodeWithText("Неверный логин или пароль").assertIsDisplayed()
        }
    }

    @Test
    fun testFinishedEventsList() {
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
            onNodeWithText("Прошедшие")
                .assertIsDisplayed()
        }
    }



    @Test
    fun testQRScreen() {
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
            onNodeWithText("QR-код").assertIsDisplayed().performClick()

        }
    }

/*    @Test
    fun testEditAcc() {
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
            onNodeWithText("Аккаунт").assertIsDisplayed().performClick()
            onNodeWithText("Редактировать аккаунт").assertIsDisplayed().performClick()
            onNodeWithText("Имя пользователя").assertIsDisplayed().performTextInput("Ivan")
            onNodeWithText("Фамилия").assertIsDisplayed().performTextInput("Ivanov")
            onNodeWithText("Сохранить изменения").assertIsDisplayed().performClick()
            waitForIdle()
            onNodeWithText("Имя пользователя").assertIsDisplayed()//.assertTextContains("Иван")
            onNodeWithText("Фамилия").assertIsDisplayed()//.assertTextContains("Иванов")
        }
    }*/



    /*@Test
    fun testEventsList() {
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
            onNodeWithText("Авторы").assertIsDisplayed().performClick()
            waitForIdle()
            onNodeWithText("Avanti!").assertIsDisplayed()

        }
    }*/



    /*@Test
    fun testEventsList() {
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
            onNodeWithText("Мероприятия").assertIsDisplayed()
        }
    }
    @Test
    fun testRankingList() {
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
            onNodeWithText("Рейтинги").assertIsDisplayed()
        }
    }

    @Test
    fun testList() {
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
            onNodeWithText("Рейтинги").assertIsDisplayed()
        }
    }
*/
}