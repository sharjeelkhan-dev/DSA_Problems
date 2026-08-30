package problems

class Problem8 {

    fun myAtoi(s: String): Int {
        val str = s.trimStart()
        if (str.isEmpty()) return 0

        val sign = if (str[0] == '-') -1 else 1
        val startIndex = if (str[0] == '+' || str[0] == '-') 1 else 0

        return str.substring(startIndex)
            .takeWhile { it.isDigit() }
            .fold(0L) { acc, ch ->
                (acc * 10 + (ch - '0')).coerceAtMost(Int.MAX_VALUE.toLong() + if (sign == -1) 1 else 0)
            }
            .let { (it * sign).toInt() }
    }
}

fun main() {
    val p = Problem8()

    println(p.myAtoi("42"))
    println(p.myAtoi("-042"))
    println(p.myAtoi("1337c0d3"))
    println(p.myAtoi("0-1"))
    println(p.myAtoi("words and 987"))
    println(p.myAtoi("-91283472332"))
}