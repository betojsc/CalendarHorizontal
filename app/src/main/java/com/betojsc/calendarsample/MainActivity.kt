package com.betojsc.calendarsample

import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.betojsc.calendarhorizontal.HorizontalCalendar
import com.betojsc.calendarhorizontal.utils.HorizontalCalendarListener
import com.betojsc.calendarsample.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var horizontalCalendar: HorizontalCalendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* start 2 months ago from now */
        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, 0)

        /* end after 2 months from now */

        /* end after 2 months from now */
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 2)

        // Default Date set to Today.

        // Default Date set to Today.
        val defaultSelectedDate = Calendar.getInstance()
        binding.txtYear.text = DateFormat.format("MMMM yyyy", defaultSelectedDate).toString()

        horizontalCalendar =HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
                .configure()
                .formatTopText("EEE")
                .formatMiddleText("dd")
                .formatBottomText("EEE")
                .showTopText(true)
                .showBottomText(false)
                .textColor(Color.LTGRAY, Color.WHITE)
                .colorTextMiddle(Color.LTGRAY, Color.parseColor("#ffd54f"))
                .end()
                .defaultSelectedDate(defaultSelectedDate)
                .build()

        Log.i("Default Date", DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString())

        horizontalCalendar.setCalendarListener(object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar, position: Int) {
                val selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", date).toString()
                binding.txtYear.text = DateFormat.format("MMMM yyyy", date).toString()
                Toast.makeText(this@MainActivity, "$selectedDateStr selected!", Toast.LENGTH_SHORT).show()
                Log.i("onDateSelected", "$selectedDateStr - Position = $position")
            }
        })
    }
}