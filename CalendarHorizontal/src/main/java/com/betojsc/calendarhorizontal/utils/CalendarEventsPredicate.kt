package com.betojsc.calendarhorizontal.utils

import com.betojsc.calendarhorizontal.model.CalendarEvent
import java.util.*

interface CalendarEventsPredicate {
    fun events(date: Calendar): List<CalendarEvent>
}