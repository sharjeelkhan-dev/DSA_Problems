package problems

import kotlin.text.iterator

class Solution {
    fun maxNumberOfBalloons(text: String): Int {
        val counts = IntArray(26)

        // Count occurrences of each character
        for (char in text) {
            if (char in "balloon") {
                counts[char - 'a']++
            }
        }
        // Extract individual counts
        val b = counts['b' - 'a']
        val a = counts['a' - 'a']
        val l = counts['l' - 'a'] / 2  // Each word needs 2 'l's
        val o = counts['o' - 'a'] / 2  // Each word needs 2 'o's
        val n = counts['n' - 'a']

        // The limiting factor is our answer
        return minOf(b, minOf(a, minOf(l, minOf(o, n))))
    }
}
// Global Entry Point for Simple Programming
fun main() {
    val solution = Solution()
    val testInput = "loonbalxballpoon"
    val result = solution.maxNumberOfBalloons(testInput)

    println("=====================================")
    println("Input Text: $testInput")
    println("Output (Max Balloons): $result")
    println("=====================================")
}