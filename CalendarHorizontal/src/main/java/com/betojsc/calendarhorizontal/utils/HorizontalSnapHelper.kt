package com.betojsc.calendarhorizontal.utils

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.betojsc.calendarhorizontal.HorizontalCalendar
import com.betojsc.calendarhorizontal.HorizontalCalendarView

class HorizontalSnapHelper : LinearSnapHelper() {

    private var horizontalCalendar: HorizontalCalendar? = null
    private var calendarView: HorizontalCalendarView? = null

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
        val snapView = super.findSnapView(layoutManager)

        if (calendarView!!.scrollState !== RecyclerView.SCROLL_STATE_DRAGGING) {
            val selectedItemPosition: Int
            selectedItemPosition = if (snapView == null) {
                // no snapping required
                horizontalCalendar!!.selectedDatePosition
            } else {
                val snapDistance = calculateDistanceToFinalSnap(layoutManager!!, snapView)
                if (snapDistance!![0] != 0 || snapDistance[1] != 0) {
                    return snapView
                }
                layoutManager.getPosition(snapView)
            }
            notifyCalendarListener(selectedItemPosition)
        }

        return snapView
    }

    private fun notifyCalendarListener(selectedItemPosition: Int) {
        if (!horizontalCalendar!!.isItemDisabled(selectedItemPosition)) {
            horizontalCalendar!!.getCalendarListener()!!.onDateSelected(
                    horizontalCalendar!!.getDateAt(selectedItemPosition),
                    selectedItemPosition
                )
        }
    }

    @Throws(IllegalStateException::class)
    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        // Do nothing
    }

    @Throws(java.lang.IllegalStateException::class)
    fun attachToHorizontalCalendar(horizontalCalendar: HorizontalCalendar) {
        this.horizontalCalendar = horizontalCalendar
        calendarView = horizontalCalendar.calendarView
        super.attachToRecyclerView(calendarView)
    }

}