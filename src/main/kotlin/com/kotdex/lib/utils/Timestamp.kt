package com.kotdex.lib.utils

import java.util.*

/**
 * A value class that represents a timestamp.
 *
 * This class provides various methods to format the timestamp in different ways.
 * The formatting methods return strings in the form of "<t:timestamp:format>", where
 * 'timestamp' is the timestamp value in milliseconds since the epoch, and 'format' is
 * a format string that determines the output format.
 */
@JvmInline
value class Timestamp(val date: Date = Date()) {

    /**
     * Returns the time in 24-hour format as a string.
     *
     * The format of the returned string is "<t:timestamp:t>".
     *
     * Example:
     * ```
     * val timestamp = Timestamp()
     * val time24 = timestamp.time24() // "20:20"
     * ```
     */
    fun time24() = "<t:${date.time}:t>"

    /**
     * Returns the time in 24-hour format with seconds as a string.
     *
     * The format of the returned string is "<t:timestamp:T>".
     *
     * Example:
     * ```
     * val timestamp = Timestamp()
     * val time24Seconds = timestamp.time24Seconds() // "20:20:35"
     * ```
     */
    fun time24Seconds() = "<t:${date.time}:T>"

    /**
     * Returns the date in "dd/MM/yyyy" format as a string.
     *
     * The format of the returned string is "<t:timestamp:d>".
     *
     * Example:
     * ```
     * val timestamp = Timestamp()
     * val dateDDMMYYYY = timestamp.dateDDMMYYYY() // "18/02/2023"
     * ```
     */
    fun dateDDMMYYYY() = "<t:${date.time}:d>"

    /**
     * Returns the date in full format as a string.
     *
     * The format of the returned string is "<t:timestamp:D>".
     *
     * Example:
     * ```
     * val timestamp = Timestamp()
     * val dateFull = timestamp.dateFull() // "February 18, 2023"
     * ```
     */
    fun dateFull() = "<t:${date.time}:D>"

    /**
     * Returns the date and time in full format without the weekday as a string.
     *
     * The format of the returned string is "<t:timestamp:f>".
     *
     * Example:
     * ```
     * val timestamp = Timestamp()
     * val dateAndTimeFullNoWeekday = timestamp.dateAndTimeFullNoWeekday() // "February 18, 2023 at 20:20"
     * ```
     */
    fun dateAndTimeFullNoWeekday() = "<t:${date.time}:f>"

    /**
     * Returns the date and time in full format as a string.
     *
     * The format of the returned string is "<t:timestamp:F>".
     *
     * Example:
     * ```
     * val timestamp = Timestamp()
     * val dateAndTimeFull = timestamp.dateAndTimeFull() // "Friday, February 18, 2023 at 20:20"
     * ```
     */
    fun dateAndTimeFull() = "<t:${date.time}:F>"
}