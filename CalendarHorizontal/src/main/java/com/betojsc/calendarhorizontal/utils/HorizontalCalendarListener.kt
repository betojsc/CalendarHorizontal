package com.betojsc.calendarhorizontal.utils

import com.betojsc.calendarhorizontal.HorizontalCalendarView
import java.util.*

interface HorizontalCalendarListener {

    fun onDateSelected(date: Calendar, position: Int)

    fun onCalendarScroll(calendarView: HorizontalCalendarView, dx: Int, dy: Int) {}

    fun onDateLongClicked(date: Calendar?, position: Int): Boolean {
        return false
    }
}