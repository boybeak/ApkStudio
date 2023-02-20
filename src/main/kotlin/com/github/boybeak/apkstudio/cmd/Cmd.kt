package com.github.boybeak.apkstudio.cmd

class Cmd private constructor(val command: String){
    companion object {
        fun command(command: String): Cmd {
            return Cmd(command)
        }
        fun simpleCmd(command: String): Cmd {
            val onStartCallback: (cmd: String) -> Unit = {
                println(CmdText(it).textColor(Color.GREEN))
            }
            val onMessage: (line: String) -> Unit = {
                println(CmdText(it))
            }
            val onError: (line: String) -> Unit = {
                println(CmdText.error(it))
            }
            val onEndCallback: (success: Boolean) -> Unit = {
                println(
                    if (it) {
                        CmdText("Success").textColor(Color.GREEN)
                    } else {
                        CmdText.error("Fail")
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
    private var endCallback: ((end: Boolean) -> Unit)? = null

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
    fun onEnd(callback: (success: Boolean) -> Unit): Cmd {
        this.endCallback = callback
        return this
    }
    fun execute(): Boolean {
        startCallback?.invoke(command)
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

        endCallback?.invoke(success)

        startCallback = null
        messageCallback = null
        errorCallback = null
        endCallback = null

        return success
    }

}