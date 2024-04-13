package com.example.fresh.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import com.example.fresh.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth


@Composable
fun CalendarScreen() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.beige)),

        ){


        /*val currentDate = remember { LocalDate.now() }
        val currentMonth = remember { YearMonth.now() }
        val startDate = remember { currentMonth.minusMonths(100).atStartOfMonth() } // Adjust as needed
        val endDate = remember { currentMonth.plusMonths(100).atEndOfMonth() } // Adjust as needed
        val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library

        val state = rememberWeekCalendarState(
            startDate = startDate,
            endDate = endDate,
            firstVisibleWeekDate = currentDate,
            firstDayOfWeek = firstDayOfWeek
        )

        HorizontalCalendar(
            state = state,
            dayContent = { Day(it) },
            monthHeader = { month ->
                // You may want to use `remember {}` here so the mapping is not done
                // every time as the days of week order will never change unless
                // you set a new value for `firstDayOfWeek` in the state.
                val daysOfWeek = month.weekDays.first().map { it.date.dayOfWeek }
                MonthHeader(daysOfWeek = daysOfWeek)
            }
        )
        */
    }

}

@Composable
fun Day(day: CalendarDay) {
    Box(
        modifier = Modifier
            .aspectRatio(1f), // This is important for square sizing!
        contentAlignment = Alignment.Center
    ) {
        Text(text = day.date.dayOfMonth.toString())
    }
}