package com.github.boybeak.apkstudio

import com.github.boybeak.apkstudio.cmd.CmdText
import java.io.File

val apkFile = File("/Users/GaoYunfei/Downloads/release/app-release.apk")


fun main(vararg args: String) {
    ApkStudio.start(apkFile)
}