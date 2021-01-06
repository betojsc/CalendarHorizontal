package com.betojsc.calendarhorizontal.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.betojsc.calendarhorizontal.HorizontalCalendar
import com.betojsc.calendarhorizontal.HorizontalLayoutManager
import com.betojsc.calendarhorizontal.model.CalendarItemStyle
import com.betojsc.calendarhorizontal.utils.CalendarEventsPredicate
import com.betojsc.calendarhorizontal.utils.HorizontalCalendarPredicate
import com.betojsc.calendarhorizontal.utils.Utils
import java.util.*

abstract class HorizontalCalendarBaseAdapter<VH : DateViewHolder?, T : Calendar> constructor
(private val itemResId: Int,
 val horizontalCalendar: HorizontalCalendar,
 protected var startDate: Calendar,
 endDate: Calendar,
 private val disablePredicate: HorizontalCalendarPredicate?,
 eventsPredicate: CalendarEventsPredicate?
) : RecyclerView.Adapter<VH>() {
    private val eventsPredicate: CalendarEventsPredicate?
    private val cellWidth: Int
    private var disabledItemStyle: CalendarItemStyle? = null
    protected var itemsCount: Int
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = LayoutInflater.from(parent.context).inflate(itemResId, parent, false)
        val viewHolder = createViewHolder(itemView, cellWidth)
        viewHolder!!.itemView.setOnClickListener(MyOnClickListener(viewHolder))
        viewHolder.itemView.setOnLongClickListener(MyOnLongClickListener(viewHolder))
        if (eventsPredicate != null) {
            initEventsRecyclerView(viewHolder.eventsRecyclerView)
        } else {
            viewHolder.eventsRecyclerView.visibility = View.GONE
        }
        return viewHolder
    }

    private fun initEventsRecyclerView(recyclerView: RecyclerView) {
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = EventsAdapter(emptyList())
        val layoutManager = GridLayoutManager(recyclerView.context, 4)
        recyclerView.layoutManager = layoutManager
    }

    protected abstract fun createViewHolder(itemView: View?, cellWidth: Int): VH
    abstract fun getItem(position: Int): T
    override fun getItemCount(): Int {
        return itemsCount
    }

    fun isDisabled(position: Int): Boolean {
        if (disablePredicate == null) {
            return false
        }
        val date: Calendar = getItem(position) as Calendar
        return disablePredicate.test(date)
    }

    protected fun showEvents(viewHolder: VH, date: Calendar?) {
        if (eventsPredicate == null) {
            return
        }
        val events = eventsPredicate.events(date!!)
        if (events == null || events.isEmpty()) {
            viewHolder!!.eventsRecyclerView.visibility = View.GONE
        } else {
            viewHolder!!.eventsRecyclerView.visibility = View.VISIBLE
            val eventsAdapter = viewHolder.eventsRecyclerView.adapter as EventsAdapter?
            eventsAdapter!!.update(events)
        }
    }

    protected fun applyStyle(viewHolder: VH, date: Calendar?, position: Int) {
        val selectedItemPosition: Int = horizontalCalendar.selectedDatePosition
        if (disablePredicate != null) {
            val isDisabled = disablePredicate.test(date!!)
            viewHolder!!.itemView.isEnabled = !isDisabled
            if (isDisabled && disabledItemStyle != null) {
                applyStyle(viewHolder, disabledItemStyle!!)
                viewHolder.selectionView.visibility = View.INVISIBLE
                return
            }
        }

        // Selected Day
        if (position == selectedItemPosition) {
            applyStyle(viewHolder, horizontalCalendar.getSelectedItemStyle())
            viewHolder!!.selectionView.visibility = View.VISIBLE
        } else {
            applyStyle(viewHolder, horizontalCalendar.getDefaultStyle())
            viewHolder!!.selectionView.visibility = View.INVISIBLE
        }
    }

    protected fun applyStyle(viewHolder: VH, itemStyle: CalendarItemStyle) {
        viewHolder!!.textTop.setTextColor(itemStyle.colorTopText)
        viewHolder.textMiddle.setTextColor(itemStyle.colorMiddleText)
        viewHolder.textBottom.setTextColor(itemStyle.colorBottomText)
        if (Build.VERSION.SDK_INT >= 16) {
            viewHolder.itemView.background = itemStyle.background
        } else {
            viewHolder.itemView.setBackgroundDrawable(itemStyle.background)
        }
    }

    fun update(startDate: Calendar, endDate: Calendar?, notify: Boolean) {
        this.startDate = startDate
        itemsCount = calculateItemsCount(startDate, endDate)
        if (notify) {
            notifyDataSetChanged()
        }
    }

    protected abstract fun calculateItemsCount(startDate: Calendar?, endDate: Calendar?): Int
    private inner class MyOnClickListener internal constructor(private val viewHolder: ViewHolder) : View.OnClickListener {
        override fun onClick(v: View) {
            val position = viewHolder.adapterPosition
            if (position == -1) return
            horizontalCalendar.calendarView.setSmoothScrollSpeed(HorizontalLayoutManager.SPEED_SLOW)
            horizontalCalendar.centerCalendarToPosition(position)
        }
    }

    private inner class MyOnLongClickListener internal constructor(private val viewHolder: ViewHolder) : OnLongClickListener {
        override fun onLongClick(v: View): Boolean {
            val calendarListener = horizontalCalendar.getCalendarListener() ?: return false
            val position = viewHolder.adapterPosition
            val date: Calendar = getItem(position) as Calendar
            return calendarListener.onDateLongClicked(date, position)
        }
    }

    init {
        if (disablePredicate != null) {
            disabledItemStyle = disablePredicate.style()
        }
        this.eventsPredicate = eventsPredicate
        cellWidth = Utils.calculateCellWidth(horizontalCalendar.context, horizontalCalendar.numberOfDatesOnScreen)
        itemsCount = calculateItemsCount(startDate, endDate)
    }
}