package com.github.boybeak.apkstudio.cmd

class CmdText(val text: String) {
    companion object {
        const val ANSI_RESET = "\u001B[0m"
        fun default(text: String): CmdText {
            return CmdText(text)
        }
        fun error(text: String): CmdText {
            return CmdText(text).textColor(Color.RED)
        }
    }

    private var textColor: Color = Color.DEFAULT
    private var textStyle: Style = Style.DEFAULT
    private var highIntensityEnable = false
    private var background: CmdTextBackground? = null

    fun textColor(color: Color): CmdText {
        this.textColor = color
        return this
    }
    fun textStyle(style: Style): CmdText {
        this.textStyle = style
        return this
    }
    fun highIntensity(enable: Boolean): CmdText {
        highIntensityEnable = enable
        return this
    }
    fun background(bg: CmdTextBackground): CmdText {
        this.background = bg
        return this
    }

    override fun toString(): String {
        val colorFlag = if (highIntensityEnable) textColor.flag + 60 else textColor.flag
        val styleFlag = textStyle.flag
        val bgFlag = if (background != null) {
            background!!.toString()
        } else {
            ""
        }
        return "${bgFlag}\u001b[$styleFlag;${colorFlag}m$text$ANSI_RESET"
    }

}