package com.kotdex.internal.proxies

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import kotlin.reflect.KProperty

object SLF4J {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Logger {
        return getLogger(thisRef!!::class.java)!!
    }

    operator fun invoke(thisRef: Any?, property: Any?) = lazy {
        getLogger(thisRef!!::class.java)!!
    }

    operator fun invoke(name: String) = lazy {
        getLogger(name)!!
    }

    inline operator fun <reified T> invoke() = lazy {
        getLogger(T::class.java)!!
    }
}