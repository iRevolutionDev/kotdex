package com.kotdex.internal.proxies

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

object SLF4J {
    operator fun getValue(thisRef: Any?, property: Any?): Logger {
        return getLogger(thisRef!!::class.java)!!
    }

    operator fun invoke(thisRef: Any?, property: Any?) = lazy {
        getLogger(thisRef!!::class.java)!!
    }

    operator fun invoke(name: String) = lazy {
        getLogger(name)!!
    }

    inline operator fun <reified T> invoke(): Logger {
        return getLogger(T::class.java)
    }
}