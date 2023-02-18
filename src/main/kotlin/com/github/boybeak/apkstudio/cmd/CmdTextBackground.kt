package com.github.boybeak.apkstudio.cmd

class CmdTextBackground(val color: Color, val highIntensity: Boolean = false) {

    override fun toString(): String {
        val flag = (if (highIntensity) color.flag + 60 else color.flag) + 10
        return "\u001B[${flag}m"
    }
}