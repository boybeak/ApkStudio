package com.github.boybeak.apkstudio

import com.github.boybeak.apkstudio.cmd.CmdExecutor
import com.github.boybeak.apkstudio.cmd.CmdText
import com.github.boybeak.apkstudio.cmd.Color
import java.io.File

object ApkTool {

    private val onStartCallback: (cmd: String) -> Unit = {
        println(CmdText(it).textColor(Color.GREEN))
    }
    private val onEndCallback: (success: Boolean) -> Unit = {
        println(if (it) {
            CmdText("Success").textColor(Color.GREEN)
        } else {
            CmdText.error("Fail")
        })
    }

    fun d(apkFile: File, outputDir: File, onMessage: (line: String) -> Unit, onError: (line: String) -> Unit): Boolean {
        return CmdExecutor.command("apktool d ${apkFile.absolutePath} -o ${outputDir.absolutePath}")
            .onStart(onStartCallback)
            .onMessage(onMessage)
            .onError(onError)
            .onEnd(onEndCallback)
            .execute()
    }

    fun b(dir: File, outputApk: File, onMessage: (line: String) -> Unit, onError: (line: String) -> Unit): Boolean {
        return CmdExecutor.command("apktool b ${dir.absolutePath} -o ${outputApk.absolutePath}")
            .onStart(onStartCallback)
            .onMessage(onMessage)
            .onError(onError)
            .onEnd(onEndCallback)
            .execute()
    }

}