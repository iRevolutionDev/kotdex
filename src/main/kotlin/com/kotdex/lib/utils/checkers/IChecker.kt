package com.kotdex.lib.utils.checkers

interface IChecker<P> {
    fun check(data: P): FailedCheck?
}