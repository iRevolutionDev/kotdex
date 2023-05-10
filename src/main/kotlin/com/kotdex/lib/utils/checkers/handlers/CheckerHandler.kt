package com.kotdex.lib.utils.checkers.handlers

import com.kotdex.lib.utils.checkers.FailedCheck
import com.kotdex.lib.utils.checkers.IChecker

typealias CheckerHandlerResult = List<FailedCheck?>

class CheckerHandler<P> : ICheckerHandler<P, CheckerHandlerResult> {
    private var checkers: MutableList<IChecker<P>?> = mutableListOf()

    override fun addChecker(checker: IChecker<P>): ICheckerHandler<P, CheckerHandlerResult> {
        checkers.add(checker)

        return this
    }

    override fun handle(data: P, returnOnFirstError: Boolean): CheckerHandlerResult {
        if (checkers.isEmpty()) return emptyList()

        val errors: MutableList<FailedCheck?> = mutableListOf()

        checkers.forEach { checker ->
            val failed = checker?.check(data)
            if (failed == null) return@forEach
            if (returnOnFirstError) return listOf(failed)

            errors.add(failed)
        }

        return errors
    }
}