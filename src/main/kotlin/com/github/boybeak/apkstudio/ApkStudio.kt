package com.github.boybeak.apkstudio

import com.github.boybeak.apkstudio.cmd.CmdText
import java.io.File

object ApkStudio {
    private val workspaceRoot = File("/Users/GaoYunfei/ApkWorkspace")

    init {

    }

    fun start(apkFile: File) {
        workspaceRoot.mkdirs()
        val output = File(workspaceRoot, "decode")
//        if (output.exists()) {
//            output.deleteRecursively()
//        }
//
//        val decodeSuccess = ApkTool.d(apkFile, output, onMessage, onError)
//        if (!decodeSuccess) {
//            return
//        }

        val stringsFile = File(output, "res/values/strings.xml")
        val stringsReplaceFile = File(workspaceRoot, "strings.xml")
        stringsFile.delete()
        stringsReplaceFile.copyTo(stringsFile, true)

        val outputApkFile = File(workspaceRoot, "rebuild.apk")
        ApkTool.b(output, outputApkFile)

        /*val signedApkFile = File(workspaceRoot, "signed.apk")
        ApkSigner().sign(outputApkFile, signedApkFile)*/

    }
}