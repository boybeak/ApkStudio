package com.github.boybeak.apkstudio.cmd

class CmdExecutor private constructor(val command: String){
    companion object {
        fun command(command: String): CmdExecutor {
            return CmdExecutor(command)
        }
    }

    private var startCallback: ((command: String) -> Unit)? = null
    private var messageCallback: ((line: String) -> Unit)? = null
    private var errorCallback: ((line: String) -> Unit)? = null
    private var endCallback: ((end: Boolean) -> Unit)? = null

    fun onStart(callback: (command: String) -> Unit): CmdExecutor {
        this.startCallback = callback
        return this
    }
    fun onMessage(callback: (line: String) -> Unit): CmdExecutor {
        this.messageCallback = callback
        return this
    }
    fun onError(callback: (line: String) -> Unit): CmdExecutor {
        errorCallback = callback
        return this
    }
    fun onEnd(callback: (success: Boolean) -> Unit): CmdExecutor {
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