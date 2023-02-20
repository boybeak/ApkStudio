package com.github.boybeak.apkstudio

import com.github.boybeak.apkstudio.cmd.Cmd
import java.io.File

val apkFile = File("/Users/GaoYunfei/ApkWorkspace/CloudSchoolTeacher-京师美育-release.apk")
val apkIn = File("/Users/GaoYunfei/ApkWorkspace/rebuild.apk")
val apkAligned = File("/Users/GaoYunfei/ApkWorkspace/aligned.apk")
val apkOut = File("/Users/GaoYunfei/ApkWorkspace/signed.apk")

private val signKeyFile = File("/Users/GaoYunfei/ApkWorkspace/sign_key.jks")

fun main(vararg args: String) {

    println(System.currentTimeMillis())
    Cmd.simpleCmd("sleep 3").execute()
    Cmd.simpleCmd("echo Hello").execute()
    println(System.currentTimeMillis())

    /*Env.check(
        onSuccess = { env ->
            env.sdk.buildTools.firstOrNull()?.run {
//                zipalign.align()
//                apkSigner.sign()
            }
        }, onError = {

        }
    )*/

//    Zipalign().align(apkIn, apkAligned)
//    ApkSigner().sign(apkAligned, apkOut,
//        SignConfig.Builder(signKeyFile)
//            .alias("meishuquan")
//            .ksPass("meishuquan2015")
//            .keyPass("meishuquan2015")
//            .v1Enable(true)
//            .v2Enable(true)
//            .build()
//    )
//    ApkSigner().verify(apkOut)

//    ApkStudio.start(apkFile)
}