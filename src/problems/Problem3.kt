package problems

class Solution3 {
    fun isMatch(s: String, p: String): Boolean {
        val m = s.length
        val n = p.length

        val dp = Array(m + 1) { BooleanArray(n + 1) }

        dp[0][0] = true

        for (j in 2..n) {
            if (p[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2]
            }
        }

        // Fill the DP table
        for (i in 1..m) {
            for (j in 1..n) {
                if (p[j - 1] == s[i - 1] || p[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1]
                } else if (p[j - 1] == '*') {

                    dp[i][j] = dp[i][j - 2]

                    val precedingChar = p[j - 2]
                    if (precedingChar == s[i - 1] || precedingChar == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j]
                    }
                }
            }
        }
        return dp[m][n]
    }
}

fun main() {
    val solution = Solution3()

    val testCases = listOf(
        Triple("aa", "a", false),
        Triple("aa", "a*", true),
        Triple("ab", ".*", true),
        Triple("aab", "c*a*b", true),
        Triple("mississippi", "mis*is*p*.", false)
    )
    println("Regular Expression Matching Test Results")

    for ((s, p, expected) in testCases) {
        val actualResult = solution.isMatch(s, p)
        val status = if (actualResult == expected) "PASSED" else "FAILED"

        println("Input String : \"$s\"")
        println("Pattern      : \"$p\"")
        println("Expected     : $expected")
        println("Actual Result: $actualResult -> $status")
    }
}