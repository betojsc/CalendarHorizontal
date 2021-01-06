package com.betojsc.calendarhorizontal.adapter

import android.text.format.DateFormat
import android.util.TypedValue
import android.view.View
import com.betojsc.calendarhorizontal.HorizontalCalendar
import com.betojsc.calendarhorizontal.R
import com.betojsc.calendarhorizontal.model.HorizontalCalendarConfig
import com.betojsc.calendarhorizontal.utils.CalendarEventsPredicate
import com.betojsc.calendarhorizontal.utils.HorizontalCalendarPredicate
import com.betojsc.calendarhorizontal.utils.Utils
import java.util.*

class MonthsAdapter(
    horizontalCalendar: HorizontalCalendar?,
    startDate: Calendar?,
    endDate: Calendar?,
    disablePredicate: HorizontalCalendarPredicate?,
    eventsPredicate: CalendarEventsPredicate?
) :
    HorizontalCalendarBaseAdapter<DateViewHolder, Calendar>(
        R.layout.hc_item_calendar,
        horizontalCalendar!!,
        startDate!!,
        endDate!!,
        disablePredicate,
        eventsPredicate
    ) {

    override fun createViewHolder(itemView: View?, cellWidth: Int): DateViewHolder {
        val holder = DateViewHolder(itemView!!)
        holder.layoutContent.minimumWidth = cellWidth
        //holder.textTop.setVisibility(View.GONE);
        return holder
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val month = getItem(position)
        val config: HorizontalCalendarConfig = horizontalCalendar.getConfig()
        val selectorColor: Int? = horizontalCalendar.getConfig().selectorColor!!
        if (selectorColor != null) {
            holder.selectionView.setBackgroundColor(selectorColor)
        }
        holder.textMiddle.text =
            DateFormat.format(config.formatMiddleText, month)
        holder.textMiddle.setTextSize(TypedValue.COMPLEX_UNIT_SP, config.sizeMiddleText)
        if (config.isShowTopText) {
            holder.textTop.text =
                DateFormat.format(config.formatTopText, month)
            holder.textTop.setTextSize(TypedValue.COMPLEX_UNIT_SP, config.sizeTopText)
        } else {
            holder.textTop.visibility = View.GONE
        }
        if (config.isShowBottomText) {
            holder.textBottom.text =
                DateFormat.format(config.formatBottomText, month)
            holder.textBottom.setTextSize(TypedValue.COMPLEX_UNIT_SP, config.sizeBottomText)
        } else {
            holder.textBottom.visibility = View.GONE
        }
        showEvents(holder, month)
        applyStyle(holder, month, position)
    }

     override fun onBindViewHolder(holder: DateViewHolder, position: Int, payloads: List<Any>) {
        if (payloads == null || payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }
        val date = getItem(position)
        applyStyle(holder, date, position)
    }

    @Throws(IndexOutOfBoundsException::class)
    override fun getItem(position: Int): Calendar {
        if (position >= itemsCount) {
            throw IndexOutOfBoundsException()
        }
        val monthsDiff: Int = position - horizontalCalendar.shiftCells
        val calendar = startDate.clone() as Calendar
        calendar.add(Calendar.MONTH, monthsDiff)
        return calendar
    }

    override fun calculateItemsCount(startDate: Calendar?, endDate: Calendar?): Int {
        val days: Int = Utils.monthsBetween(startDate!!, endDate!!) + 1
        return days + horizontalCalendar.shiftCells * 2
    }
}