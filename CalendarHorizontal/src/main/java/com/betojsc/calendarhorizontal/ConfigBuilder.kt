package com.betojsc.calendarhorizontal

import android.graphics.drawable.Drawable
import android.provider.SyncStateContract
import com.betojsc.calendarhorizontal.model.CalendarItemStyle
import com.betojsc.calendarhorizontal.model.HorizontalCalendarConfig
import com.betojsc.calendarhorizontal.utils.Constants

class ConfigBuilder(private val calendarBuilder: HorizontalCalendar.Builder) {
    /* Format & Font Sizes*/
    private var sizeTopText = 0f
    private var sizeMiddleText = 0f
    private var sizeBottomText = 0f
    private var selectorColor: Int? = null
    private var formatTopText: String? = null
    private var formatMiddleText: String? = null
    private var formatBottomText: String? = null
    private var showTopText = true
    private var showBottomText = true

    /* Colors and Background*/
    private var colorTextTop = 0
    private var colorTextTopSelected = 0
    private var colorTextMiddle = 0
    private var colorTextMiddleSelected = 0
    private var colorTextBottom = 0
    private var colorTextBottomSelected = 0
    private var selectedItemBackground: Drawable? = null

    /**
     * Set the text size of the labels in scale-independent pixels
     *
     * @param sizeTopText    the Top text size, in SP
     * @param sizeMiddleText the Middle text size, in SP
     * @param sizeBottomText the Bottom text size, in SP
     */
    fun textSize(
        sizeTopText: Float, sizeMiddleText: Float,
        sizeBottomText: Float
    ): ConfigBuilder {
        this.sizeTopText = sizeTopText
        this.sizeMiddleText = sizeMiddleText
        this.sizeBottomText = sizeBottomText
        return this
    }

    /**
     * Set the text size of the top label in scale-independent pixels
     *
     * @param size the Top text size, in SP
     */
    fun sizeTopText(size: Float): ConfigBuilder {
        sizeTopText = size
        return this
    }

    /**
     * Set the text size of the middle label in scale-independent pixels
     *
     * @param size the Middle text size, in SP
     */
    fun sizeMiddleText(size: Float): ConfigBuilder {
        sizeMiddleText = size
        return this
    }

    /**
     * Set the text size of the bottom label in scale-independent pixels
     *
     * @param size the Bottom text size, in SP
     */
    fun sizeBottomText(size: Float): ConfigBuilder {
        sizeBottomText = size
        return this
    }

    fun selectorColor(selectorColor: Int?): ConfigBuilder {
        this.selectorColor = selectorColor
        return this
    }

    fun formatTopText(format: String?): ConfigBuilder {
        formatTopText = format
        return this
    }

    fun formatMiddleText(format: String?): ConfigBuilder {
        formatMiddleText = format
        return this
    }

    fun formatBottomText(format: String?): ConfigBuilder {
        formatBottomText = format
        return this
    }

    fun showTopText(value: Boolean): ConfigBuilder {
        showTopText = value
        return this
    }

    fun showBottomText(value: Boolean): ConfigBuilder {
        showBottomText = value
        return this
    }

    fun textColor(textColorNormal: Int, textColorSelected: Int): ConfigBuilder {
        colorTextTop = textColorNormal
        colorTextMiddle = textColorNormal
        colorTextBottom = textColorNormal
        colorTextTopSelected = textColorSelected
        colorTextMiddleSelected = textColorSelected
        colorTextBottomSelected = textColorSelected
        return this
    }

    fun colorTextTop(textColorNormal: Int, textColorSelected: Int): ConfigBuilder {
        colorTextTop = textColorNormal
        colorTextTopSelected = textColorSelected
        return this
    }

    fun colorTextMiddle(textColorNormal: Int, textColorSelected: Int): ConfigBuilder {
        colorTextMiddle = textColorNormal
        colorTextMiddleSelected = textColorSelected
        return this
    }

    fun colorTextBottom(textColorNormal: Int, textColorSelected: Int): ConfigBuilder {
        colorTextBottom = textColorNormal
        colorTextBottomSelected = textColorSelected
        return this
    }

    fun selectedDateBackground(background: Drawable?): ConfigBuilder {
        selectedItemBackground = background
        return this
    }

    fun end(): HorizontalCalendar.Builder {
        if (formatMiddleText == null) {
            formatMiddleText = Constants.DEFAULT_FORMAT_TEXT_MIDDLE
        }
        if (formatTopText == null && showTopText) {
            formatTopText = Constants.DEFAULT_FORMAT_TEXT_TOP
        }
        if (formatBottomText == null && showBottomText) {
            formatBottomText = Constants.DEFAULT_FORMAT_TEXT_BOTTOM
        }
        return calendarBuilder
    }

    fun createConfig(): HorizontalCalendarConfig {
        val config =
            HorizontalCalendarConfig(sizeTopText, sizeMiddleText, sizeBottomText, selectorColor)
        config.setFormatTopText(formatTopText)
        config.setFormatMiddleText(formatMiddleText)
        config.setFormatBottomText(formatBottomText)
        config.setShowTopText(showTopText)
        config.setShowBottomText(showBottomText)
        return config
    }

    fun createDefaultStyle(): CalendarItemStyle {
        return CalendarItemStyle(colorTextTop, colorTextMiddle, colorTextBottom, null)
    }

    fun createSelectedItemStyle(): CalendarItemStyle {
        return CalendarItemStyle(
            colorTextTopSelected,
            colorTextMiddleSelected,
            colorTextBottomSelected,
            selectedItemBackground
        )
    }
}
