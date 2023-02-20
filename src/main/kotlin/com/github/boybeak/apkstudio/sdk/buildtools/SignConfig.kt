package com.github.boybeak.apkstudio.sdk.buildtools

import java.io.File

data class SignConfig(val signKeyFile: File, val alias: String, val ksPass: String, val keyPass: String,
                      val v1Enable: Boolean, val v2Enable: Boolean) {
    class Builder(private val singKeyFile: File) {

        private var alias: String? = null
        private var ksPass: String? = null
        private var keyPass: String? = null

        private var v1Enable: Boolean = true
        private var v2Enable: Boolean = true

        fun alias(alias: String): Builder {
            this.alias = alias
            return this
        }

        fun ksPass(ksPass: String): Builder {
            this.ksPass = ksPass
            return this
        }

        fun keyPass(keyPass: String): Builder {
            this.keyPass = keyPass
            return this
        }

        fun v1Enable(v1Enable: Boolean): Builder {
            this.v1Enable = v1Enable
            return this
        }

        fun v2Enable(v2Enable: Boolean): Builder {
            this.v2Enable = v2Enable
            return this
        }

        fun build(): SignConfig {
            val aliasFinal = alias ?: throw IllegalStateException("alias not configured")
            val ksPassFinal = ksPass ?: throw IllegalStateException("ksPass not configured")
            val keyPassFinal = keyPass ?: throw IllegalStateException("keyPass not configured")
            return SignConfig(singKeyFile, aliasFinal, ksPassFinal, keyPassFinal, v1Enable, v2Enable)
        }

    }
}