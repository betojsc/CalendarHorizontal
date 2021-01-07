package com.betojsc.calendarsample

import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.betojsc.calendarhorizontal.HorizontalCalendar
import com.betojsc.calendarhorizontal.utils.HorizontalCalendarListener
import com.betojsc.calendarsample.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), HorizontalCalendarListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var horizontalCalendar: HorizontalCalendar

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* start 2 months ago from now */
        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, 0)

        /* end after 2 months from now */
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 2)

        // Default Date set to Today.
        val defaultSelectedDate = Calendar.getInstance()
        binding.txtYear.text = DateFormat.format("MMMM yyyy", defaultSelectedDate).toString()
        binding.imgToday.setOnClickListener {horizontalCalendar.goToday(false)
        }

        horizontalCalendar =HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .configure()
                .formatTopText("EEE")
                .formatMiddleText("dd")
                .formatBottomText("EEE")
                .showTopText(true)
                .showBottomText(false)
                .textColor(Color.LTGRAY, Color.WHITE)
                .colorTextTop(Color.LTGRAY, Color.parseColor("#BC5837"))
                .colorTextMiddle(Color.LTGRAY, Color.parseColor("#BC5837"))
                .end()
                .defaultSelectedDate(defaultSelectedDate)
                .build()

        Log.e("TAG", DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString())

        horizontalCalendar.setCalendarListener(this)

    }

    override fun onDateSelected(date: Calendar, position: Int) {
        val selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", date).toString()
        binding.txtYear.text = DateFormat.format("MMMM yyyy", date).toString()
        Toast.makeText(this@MainActivity, "$selectedDateStr selected!", Toast.LENGTH_SHORT).show()
        Log.e("TAG", "$selectedDateStr - Position = $position")
    }
}