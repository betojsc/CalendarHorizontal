package com.betojsc.calendarhorizontal.utils

import android.content.Context
import android.graphics.Point
import android.view.ViewGroup
import android.view.WindowManager
import java.util.*
import java.util.concurrent.TimeUnit

object Utils {

    fun calculateCellWidth(context: Context, itemsOnScreen: Int): Int {
        val windowManager : WindowManager? = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (windowManager != null) {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            val screenWidth = size.x
            return screenWidth / itemsOnScreen
        }
        return ViewGroup.LayoutParams.WRAP_CONTENT
    }

    fun calculateRelativeCenterPosition(position: Int, centerItem: Int, shiftCells: Int): Int {
        var relativeCenterPosition = position
        if (position > centerItem) {
            relativeCenterPosition = position + shiftCells
        } else if (position < centerItem) {
            relativeCenterPosition = position - shiftCells
        }
        return relativeCenterPosition
    }

    fun isSameDate(calendar1: Calendar, calendar2: Calendar): Boolean {
        val day = calendar1[Calendar.DAY_OF_MONTH]
        return (isSameMonth(calendar1, calendar2)
                && day == calendar2[Calendar.DAY_OF_MONTH])
    }

    fun isSameMonth(calendar1: Calendar, calendar2: Calendar): Boolean {
        val month = calendar1[Calendar.MONTH]
        val year = calendar1[Calendar.YEAR]
        return (year == calendar2[Calendar.YEAR]
                && month == calendar2[Calendar.MONTH])
    }

    fun isDateBefore(date: Calendar, origin: Calendar): Boolean {
        val dayOfYear = date[Calendar.DAY_OF_YEAR]
        val year = date[Calendar.YEAR]
        return if (year < origin[Calendar.YEAR]) {
            true
        } else year == origin[Calendar.YEAR] && dayOfYear < origin[Calendar.DAY_OF_YEAR]
    }

    fun isDateAfter(date: Calendar, origin: Calendar): Boolean {
        val dayOfYear = date[Calendar.DAY_OF_YEAR]
        val year = date[Calendar.YEAR]
        return if (year > origin[Calendar.YEAR]) {
            true
        } else year == origin[Calendar.YEAR] && dayOfYear > origin[Calendar.DAY_OF_YEAR]
    }

    fun daysBetween(startInclusive: Calendar, endExclusive: Calendar): Int {
        zeroTime(startInclusive)
        zeroTime(endExclusive)
        val diff = endExclusive.timeInMillis - startInclusive.timeInMillis //result in millis
        return TimeUnit.MILLISECONDS.toDays(diff).toInt()
    }

    fun monthsBetween(startInclusive: Calendar, endExclusive: Calendar): Int {
        val startMonth = startInclusive[Calendar.MONTH]
        val endMonth = endExclusive[Calendar.MONTH]
        val startYear = startInclusive[Calendar.YEAR]
        val endYear = endExclusive[Calendar.YEAR]
        val yearsDiff = endYear - startYear
        return endMonth - startMonth + yearsDiff * 12
    }

    fun zeroTime(calendar: Calendar) {
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        calendar[Calendar.DST_OFFSET] = 0
    }

}