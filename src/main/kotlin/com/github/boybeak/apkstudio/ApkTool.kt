package com.github.boybeak.apkstudio

import com.github.boybeak.apkstudio.cmd.Cmd
import com.github.boybeak.apkstudio.cmd.CmdText
import com.github.boybeak.apkstudio.cmd.Color
import java.io.File

object ApkTool {

    fun d(apkFile: File, outputDir: File): Boolean {
        return Cmd.simpleCmd("apktool d ${apkFile.absolutePath} -o ${outputDir.absolutePath}")
            .execute()
    }

    fun b(dir: File, outputApk: File): Boolean {
        return Cmd.command("apktool b ${dir.absolutePath} -o ${outputApk.absolutePath}")
            .execute()
    }

}