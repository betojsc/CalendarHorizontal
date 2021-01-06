package com.betojsc.calendarhorizontal.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.betojsc.calendarhorizontal.R

class DateViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

    var textTop: TextView = rootView.findViewById(R.id.hc_text_top)
    var textMiddle: TextView = rootView.findViewById(R.id.hc_text_middle)
    var textBottom: TextView = rootView.findViewById(R.id.hc_text_bottom)
    var selectionView: ImageView = rootView.findViewById(R.id.hc_selector)
    var layoutContent: View = rootView.findViewById(R.id.hc_layoutContent)
    var eventsRecyclerView: RecyclerView = rootView.findViewById(R.id.hc_events_recyclerView)

}