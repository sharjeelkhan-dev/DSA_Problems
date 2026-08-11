package problems

import kotlin.text.iterator

class Problem6 {

    private val letters = arrayOf(
        "", "", "abc", "def", "ghi",
        "jkl", "mno", "pqrs", "tuv", "wxyz"
    )

    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()

        val result = mutableListOf<String>()

        fun backtrack(i: Int, current: StringBuilder) {
            if (i == digits.length) {
                result.add(current.toString())
                return
            }

            for (ch in letters[digits[i] - '0']) {
                current.append(ch)
                backtrack(i + 1, current)
                current.deleteCharAt(current.lastIndex)
            }
        }

        backtrack(0, StringBuilder())
        return result
    }
}

fun main() {
    val p = Problem6()

    println(p.letterCombinations("23"))
    println(p.letterCombinations("2"))
    println(p.letterCombinations(""))
}