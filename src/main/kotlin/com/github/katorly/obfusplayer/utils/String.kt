package com.github.katorly.obfusplayer.utils

/**
 * ObfusPlayer
 * utils.String
 *
 * @author Katorly
 * @since 1.0.0
 */

/**
 * Replace multiple values in a String.
 * Used StringBuilder instead of using replace() in a loop.
 *
 * @author Katorly
 * @param re values to replace
 * @return [String] the final String
 */
fun String.multiReplace(vararg re: Pair<String, Any>): String {
    val s = StringBuilder(this)
    for ((o, new) in re) {
        val n = new.toString()
        var start = s.indexOf(o)
        while (start != -1) {
            val end = start + o.length
            s.replace(start, end, n)
            start = s.indexOf(o, start + n.length)
        }
    }
    return s.toString()
}