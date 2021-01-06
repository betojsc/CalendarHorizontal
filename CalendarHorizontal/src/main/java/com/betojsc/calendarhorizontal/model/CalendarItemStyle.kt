package com.betojsc.calendarhorizontal.model

import android.graphics.drawable.Drawable

class CalendarItemStyle {
    var colorTopText = 0
        private set
    var colorMiddleText = 0
        private set
    var colorBottomText = 0
        private set
    var background: Drawable? = null
        private set

    constructor() {}
    constructor(textColor: Int, background: Drawable?) : this(
        textColor,
        textColor,
        textColor,
        background
    ) {
    }

    constructor(
        colorTopText: Int,
        colorMiddleText: Int,
        colorBottomText: Int,
        background: Drawable?
    ) {
        this.colorTopText = colorTopText
        this.colorMiddleText = colorMiddleText
        this.colorBottomText = colorBottomText
        this.background = background
    }

    fun setColorTopText(colorTopText: Int): CalendarItemStyle {
        this.colorTopText = colorTopText
        return this
    }

    fun setColorMiddleText(colorMiddleText: Int): CalendarItemStyle {
        this.colorMiddleText = colorMiddleText
        return this
    }

    fun setColorBottomText(colorBottomText: Int): CalendarItemStyle {
        this.colorBottomText = colorBottomText
        return this
    }

    fun setBackground(background: Drawable?): CalendarItemStyle {
        this.background = background
        return this
    }

    fun setupDefaultValues(defaultValues: CalendarItemStyle?) {
        if (defaultValues == null) {
            return
        }
        if (colorTopText == 0) {
            colorTopText = defaultValues.colorTopText
        }
        if (colorMiddleText == 0) {
            colorMiddleText = defaultValues.colorMiddleText
        }
        if (colorBottomText == 0) {
            colorBottomText = defaultValues.colorBottomText
        }
        if (background == null) {
            background = defaultValues.background
        }
    }
}