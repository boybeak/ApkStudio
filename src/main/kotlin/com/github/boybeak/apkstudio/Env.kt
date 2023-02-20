package com.github.boybeak.apkstudio

import com.github.boybeak.apkstudio.cmd.Cmd
import com.github.boybeak.apkstudio.cmd.CmdText
import com.github.boybeak.apkstudio.cmd.Color
import com.github.boybeak.apkstudio.sdk.SDK
import java.io.File

class Env(val sdk: SDK) {
    companion object {
        fun check(onSuccess: (env: Env) -> Unit, onError: () -> Unit) {
            /*Cmd.command("echo \"$(ANDROID_HOME)\"")
                .onMessage {
                    println(CmdText(it).textColor(Color.GREEN))
                }.execute()*/
            Cmd.command("which adb")
                .onMessage {
                    println(it)
                    if (it.trim().isNotEmpty()) {
                        val adbFile = File(it)
                        if (adbFile.exists()) {
                            onSuccess.invoke(Env(SDK(adbFile.parentFile.parentFile)))
                        }
                    } else {

                    }
                }.execute()
        }
    }

}