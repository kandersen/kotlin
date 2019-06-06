/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen.debugInformation
import org.jetbrains.kotlin.test.clientserver.MessageHeader
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.net.URL

class TestProxy(val serverPort: Int, val testClass: String, val classPath: List<URL>) {

    fun runTest(): String {
        return Socket("localhost", serverPort).use { clientSocket ->

            val output = ObjectOutputStream(clientSocket.getOutputStream())
            try {
                output.writeObject(MessageHeader.NEW_TEST)
                output.writeObject(testClass)
                output.writeObject("box")

                output.writeObject(MessageHeader.CLASS_PATH)
                //filter out jdk libs
                output.writeObject(filterOutJdkJars(classPath).toTypedArray())
                return "OK"
            } finally {
                output.close()
            }
        }
    }

    fun filterOutJdkJars(classPath: List<URL>): List<URL> {
        val javaHome = System.getProperty("java.home")
        val javaFolder = File(javaHome)
        return classPath.filterNot {
            File(it.file).startsWith(javaFolder)
        }
    }
}
