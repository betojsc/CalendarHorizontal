package com.betojsc.calendarhorizontal.utils

import com.betojsc.calendarhorizontal.model.CalendarItemStyle
import java.util.*

interface HorizontalCalendarPredicate {

    fun test(date: Calendar): Boolean

    fun style(): CalendarItemStyle

    class Or(
        private val firstPredicate: HorizontalCalendarPredicate,
        private val secondPredicate: HorizontalCalendarPredicate
    ) :
        HorizontalCalendarPredicate {
        override fun test(date: Calendar): Boolean {
            return firstPredicate.test(date) || secondPredicate.test(date)
        }

        override fun style(): CalendarItemStyle {
            return firstPredicate.style()
        }
    }
}