package com.betojsc.calendarhorizontal.adapter

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.betojsc.calendarhorizontal.R
import com.betojsc.calendarhorizontal.model.CalendarEvent

class EventsAdapter(eventList: List<CalendarEvent>) :
    RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private var eventList: List<CalendarEvent>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val context = parent.context
        val imageView = ImageView(context)
        val circle: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_circle_white_8dp)
        val drawableWrapper: Drawable = DrawableCompat.wrap(circle!!)
        imageView.setImageDrawable(drawableWrapper)
        return EventViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event: CalendarEvent = getItem(position)
        val imageView = holder.itemView as ImageView
        imageView.contentDescription = event.description
        DrawableCompat.setTint(imageView.drawable, event.color)
    }

    @Throws(IndexOutOfBoundsException::class)
    fun getItem(position: Int): CalendarEvent {
        return eventList[position]
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    fun update(eventList: List<CalendarEvent>) {
        this.eventList = eventList
        notifyDataSetChanged()
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.eventList = eventList
    }
}