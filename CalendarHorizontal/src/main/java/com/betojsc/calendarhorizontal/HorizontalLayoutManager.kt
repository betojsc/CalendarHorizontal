package com.betojsc.calendarhorizontal

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class HorizontalLayoutManager(context: Context, reverseLayout: Boolean) :
    LinearLayoutManager(context, HORIZONTAL, reverseLayout) {
    var smoothScrollSpeed = SPEED_NORMAL

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State?,
        position: Int
    ) {
        val smoothScroller: LinearSmoothScroller =
            object : LinearSmoothScroller(recyclerView.context) {
                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                    return smoothScrollSpeed / displayMetrics.densityDpi
                }
            }
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    @JvmName("getSmoothScrollSpeed1")
    fun getSmoothScrollSpeed(): Float {
        return smoothScrollSpeed
    }

    @JvmName("setSmoothScrollSpeed1")
    fun setSmoothScrollSpeed(smoothScrollSpeed: Float) {
        this.smoothScrollSpeed = smoothScrollSpeed
    }

    companion object {
        const val SPEED_NORMAL = 90f
        const val SPEED_SLOW = 125f
    }
}