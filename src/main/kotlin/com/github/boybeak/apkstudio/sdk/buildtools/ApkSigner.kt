package com.github.boybeak.apkstudio.sdk.buildtools

import com.github.boybeak.apkstudio.cmd.Cmd
import java.io.File

class ApkSigner(home: File) {

    private val apkSingerSh = File(home, "apksigner").absolutePath


    fun verify(apkFile: File) {
        Cmd.simpleCmd("apksigner verify ${apkFile.absolutePath}")
    }

    fun sign(apkIn: File, apkOut: File, config: SignConfig) {
        val builder = StringBuilder()
        val command = builder
            .append("$apkSingerSh sign -verbose --ks ${config.signKeyFile.absolutePath} ")
            .append("--v1-signing-enabled ${config.v1Enable} ")
            .append("--v2-signing-enabled ${config.v2Enable} ")
            .append("--ks-key-alias ${config.alias} ")
            .append("--ks-pass pass:${config.ksPass} ")
            .append("--key-pass pass:${config.keyPass} ")
            .append("--out ${apkOut.absolutePath} ")
            .append(apkIn.absolutePath)
            .toString()
        Cmd.simpleCmd(command).execute()
    }
}