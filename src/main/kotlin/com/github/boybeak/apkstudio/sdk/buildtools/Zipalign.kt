package com.github.boybeak.apkstudio.sdk.buildtools

import com.github.boybeak.apkstudio.cmd.Cmd
import java.io.File

class Zipalign(home: File) {
    private val zipAlignSh = File(home, "zipalign").absolutePath
    fun align(apkIn: File, apkOut: File) {
        Cmd.simpleCmd("$zipAlignSh -v 4 ${apkIn.absolutePath} ${apkOut.absolutePath}").execute()
    }
}