package com.github.boybeak.apkstudio.cmd

import java.text.DecimalFormat

class Cmd private constructor(val command: String){
    companion object {
        fun command(command: String): Cmd {
            return Cmd(command)
        }
        fun simpleCmd(command: String): Cmd {
            val onStartCallback: (cmd: String) -> Unit = {
                println(CmdText("[$it]").textColor(Color.CYAN))
            }
            val onMessage: (line: String) -> Unit = {
                println(CmdText(it))
            }
            val onError: (line: String) -> Unit = {
                println(CmdText.error(it))
            }
            val onEndCallback: (cmd: String, success: Boolean, cost: Long) -> Unit = { cmd, success, cost ->
                val formatter = DecimalFormat("0.00")
                val cmdSimplify = if (cmd.length > 20) "${cmd.substringBeforeLast(' ')} ... ${cmd.substringAfterLast(' ')}" else cmd
                println(
                    if (success) {
                        CmdText("Success(${formatter.format(cost / 1000F)}s) - [${cmdSimplify}]\n").textColor(Color.GREEN)
                    } else {
                        CmdText.error("Fail(${formatter.format(cost / 1000F)}s) - [${cmdSimplify}]\n")
                    }
                )
            }
            return Cmd(command)
                .onStart(onStartCallback)
                .onMessage(onMessage)
                .onError(onError)
                .onEnd(onEndCallback)
        }
    }

    private var startCallback: ((command: String) -> Unit)? = null
    private var messageCallback: ((line: String) -> Unit)? = null
    private var errorCallback: ((line: String) -> Unit)? = null
    private var endCallback: ((cmd: String, success: Boolean, cost: Long) -> Unit)? = null

    fun onStart(callback: (command: String) -> Unit): Cmd {
        this.startCallback = callback
        return this
    }
    fun onMessage(callback: (line: String) -> Unit): Cmd {
        this.messageCallback = callback
        return this
    }
    fun onError(callback: (line: String) -> Unit): Cmd {
        errorCallback = callback
        return this
    }
    fun onEnd(callback: (cmd: String, success: Boolean, cost: Long) -> Unit): Cmd {
        this.endCallback = callback
        return this
    }
    fun execute(): Boolean {
        startCallback?.invoke(command)

        val startAt = System.currentTimeMillis()

        val process = Runtime.getRuntime().exec(command)

        var messageStr: String?
        var success = true
        do {
            messageStr = process.inputReader().readLine()
            if (messageStr != null) {
                messageCallback?.invoke(messageStr)
            }
        } while (messageStr != null)

        var errorStr: String?
        do {
            errorStr = process.errorReader().readLine()
            if (errorStr != null) {
                success = false
                errorCallback?.invoke(errorStr)
            }
        } while (errorStr != null)

        endCallback?.invoke(command, success, System.currentTimeMillis() - startAt)

        startCallback = null
        messageCallback = null
        errorCallback = null
        endCallback = null

        return success
    }

}