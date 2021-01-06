package com.betojsc.calendarhorizontal.utils

import com.betojsc.calendarhorizontal.HorizontalCalendarView
import java.util.*

abstract class HorizontalCalendarListener {

    abstract fun onDateSelected(date: Calendar, position: Int)

    open fun onCalendarScroll(calendarView: HorizontalCalendarView, dx: Int, dy: Int) {}

    open fun onDateLongClicked(date: Calendar?, position: Int): Boolean {
        return false
    }
}