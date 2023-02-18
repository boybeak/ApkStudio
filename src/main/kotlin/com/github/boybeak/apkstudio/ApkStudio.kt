package com.github.boybeak.apkstudio

import com.github.boybeak.apkstudio.cmd.CmdText
import java.io.File

object ApkStudio {
    private val workspaceRoot = File("/Users/GaoYunfei/ApkWorkspace")
    private val onMessage: (line: String) -> Unit = {
        println(CmdText(it))
    }
    private val onError: (line: String) -> Unit = {
        println(CmdText.error(it))
    }
    fun start(apkFile: File) {
        workspaceRoot.mkdirs()
        val output = File(workspaceRoot, "decode")
        if (output.exists()) {
            output.deleteRecursively()
        }

        val decodeSuccess = ApkTool.d(apkFile, output, onMessage, onError)
        if (!decodeSuccess) {
            return
        }
        val outputApkFile = File(workspaceRoot, "rebuild.apk")
        ApkTool.b(output, outputApkFile, onMessage, onError)

    }
}