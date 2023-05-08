package com.kotdex.lib.builders

interface IBuilder<out R> {
    fun build(): R
}