package com.github.boybeak.apkstudio.sdk.buildtools

import java.io.File

class BuildTools(private val home: File) {
    val version = home.name
    val apkSigner: ApkSigner = ApkSigner(home)
    val zipalign: Zipalign = Zipalign(home)
}