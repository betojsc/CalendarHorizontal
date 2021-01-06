package com.betojsc.calendarhorizontal.model

class HorizontalCalendarConfig {
    /* Format & Font Sizes*/
    var formatTopText: String? = null
        private set
    var formatMiddleText: String? = null
        private set
    var formatBottomText: String? = null
        private set
    var sizeTopText = 0f
        private set
    var sizeMiddleText = 0f
        private set
    var sizeBottomText = 0f
        private set
    var selectorColor: Int? = null
        private set
    var isShowTopText = false
        private set
    var isShowBottomText = false
        private set

    constructor() {}
    constructor(
        sizeTopText: Float,
        sizeMiddleText: Float,
        sizeBottomText: Float,
        selectorColor: Int?
    ) {
        this.sizeTopText = sizeTopText
        this.sizeMiddleText = sizeMiddleText
        this.sizeBottomText = sizeBottomText
        this.selectorColor = selectorColor
    }

    fun setFormatTopText(formatTopText: String?): HorizontalCalendarConfig {
        this.formatTopText = formatTopText
        return this
    }

    fun setFormatMiddleText(formatMiddleText: String?): HorizontalCalendarConfig {
        this.formatMiddleText = formatMiddleText
        return this
    }

    fun setFormatBottomText(formatBottomText: String?): HorizontalCalendarConfig {
        this.formatBottomText = formatBottomText
        return this
    }

    fun setSizeTopText(sizeTopText: Float): HorizontalCalendarConfig {
        this.sizeTopText = sizeTopText
        return this
    }

    fun setSizeMiddleText(sizeMiddleText: Float): HorizontalCalendarConfig {
        this.sizeMiddleText = sizeMiddleText
        return this
    }

    fun setSizeBottomText(sizeBottomText: Float): HorizontalCalendarConfig {
        this.sizeBottomText = sizeBottomText
        return this
    }

    fun setSelectorColor(selectorColor: Int?): HorizontalCalendarConfig {
        this.selectorColor = selectorColor
        return this
    }

    fun setShowTopText(showTopText: Boolean): HorizontalCalendarConfig {
        isShowTopText = showTopText
        return this
    }

    fun setShowBottomText(showBottomText: Boolean): HorizontalCalendarConfig {
        isShowBottomText = showBottomText
        return this
    }

    fun setupDefaultValues(defaultConfig: HorizontalCalendarConfig?) {
        if (defaultConfig == null) {
            return
        }
        if (selectorColor == null) {
            selectorColor = defaultConfig.selectorColor
        }
        if (sizeTopText == 0f) {
            sizeTopText = defaultConfig.sizeTopText
        }
        if (sizeMiddleText == 0f) {
            sizeMiddleText = defaultConfig.sizeMiddleText
        }
        if (sizeBottomText == 0f) {
            sizeBottomText = defaultConfig.sizeBottomText
        }
    }
}