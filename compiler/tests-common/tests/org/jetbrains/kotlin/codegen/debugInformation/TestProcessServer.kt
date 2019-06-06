/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen.debugInformation

import org.jetbrains.kotlin.test.clientserver.MessageHeader
import org.jetbrains.kotlin.test.clientserver.getBoxMethodOrNull
import org.jetbrains.kotlin.test.clientserver.getGeneratedClass
import java.io.File
import java.io.ObjectInputStream
import java.net.ServerSocket
import java.net.Socket
import java.net.URL
import java.net.URLClassLoader
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

object TestProcessServer {

    private val executor = Executors.newFixedThreadPool(1)!!

    @Volatile
    private var isProcessingTask = true

    @Volatile
    private var lastTime = System.currentTimeMillis()

    private val scheduler = Executors.newScheduledThreadPool(1)

    private lateinit var handler: ScheduledFuture<*>

    private lateinit var serverSocket: ServerSocket

    @JvmStatic
    fun main(args: Array<String>) {
        sheduleShutdownProcess()

        try {
            while (true) {
                lastTime = System.currentTimeMillis()
                isProcessingTask = false

                val serverSocket = ServerSocket(0)
                println(serverSocket.localPort)
                val clientSocket = serverSocket.accept()
                isProcessingTask = true
                executor.execute(ServerTest(clientSocket))
                serverSocket.close()
            }
        } finally {
            handler.cancel(false)
            scheduler.shutdown()
        }
    }

    private fun sheduleShutdownProcess() {
        handler = scheduler.scheduleAtFixedRate(
            {
                if (!isProcessingTask && (System.currentTimeMillis() - lastTime) >= 60 * 1000 /*60 sec*/) { serverSocket.close() }
            }, 60, 60, TimeUnit.SECONDS
        )
    }
}

private class ServerTest(val clientSocket: Socket) : Runnable {
    private lateinit var className: String
    private lateinit var testMethod: String

    override fun run() {
        val input = ObjectInputStream(clientSocket.getInputStream())
        try {
            var message = input.readObject() as MessageHeader
            assert(message == MessageHeader.NEW_TEST, { "New test marker missed, but $message received" })
            className = input.readObject() as String
            testMethod = input.readObject() as String

            message = input.readObject() as MessageHeader
            assert(message == MessageHeader.CLASS_PATH, { "Class path marker missed, but $message received" })
            @Suppress("UNCHECKED_CAST")
            val classPath = input.readObject() as Array<URL>

            val result = executeTest(URLClassLoader(classPath, JDK_EXT_JARS_CLASS_LOADER))
        } finally {
            input.close()
            clientSocket.close()
        }
    }

    fun executeTest(classLoader: ClassLoader): String {
        val clazz = getGeneratedClass(classLoader, className)
        return getBoxMethodOrNull(clazz)!!.invoke(null) as String
    }

    companion object {
        //Required for org.jetbrains.kotlin.codegen.BlackBoxCodegenTestGenerated.FullJdk#testClasspath
        val JDK_EXT_JARS_CLASS_LOADER: ClassLoader

        init {
            val javaHome = System.getProperty("java.home")
            val extFolder = File(javaHome + "/lib/ext/")
            val listFiles = extFolder.listFiles()
            val additionalJars = listFiles?.filter { it.name.endsWith(".jar") }?.map { it.toURI().toURL() } ?: emptyList()
            JDK_EXT_JARS_CLASS_LOADER = URLClassLoader(additionalJars.toTypedArray(), null)
        }
    }
}
