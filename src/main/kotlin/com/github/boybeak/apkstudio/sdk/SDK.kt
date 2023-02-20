package com.github.boybeak.apkstudio.sdk

import com.github.boybeak.apkstudio.sdk.buildtools.BuildTools
import java.io.File

class SDK(val root: File) {
    val adb = File(root, "platform-tools${File.separator}adb")
    private val buildToolsDir = File(root, "build-tools")
    val buildTools = buildToolsDir.listFiles()?.map {
        BuildTools(it)
    } ?: emptyList()
}