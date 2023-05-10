package com.kotdex.lib.utils.checkers.handlers

import com.kotdex.lib.utils.checkers.IChecker

interface ICheckerHandler<P, R> {
    fun addChecker(checker: IChecker<P>): ICheckerHandler<P, R>

    fun handle(data: P, returnOnFirstError: Boolean = false): R
}