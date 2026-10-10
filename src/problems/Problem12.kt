package problems

class Problem12 {
    fun isMatch(s: String, p: String): Boolean {
        val (n, m) = s.length to p.length
        val dp = Array(n + 1) { BooleanArray(m + 1) }

        dp[0][0] = true
        for (j in 1..m) if (p[j - 1] == '*') dp[0][j] = dp[0][j - 1]

        for (i in 1..n) {
            for (j in 1..m) {
                dp[i][j] = when (p[j - 1]) {
                    s[i - 1], '?' -> dp[i - 1][j - 1]
                    '*' -> dp[i - 1][j] || dp[i][j - 1]
                    else -> false
                }
            }
        }
        return dp[n][m]
    }
}

fun main() {
    val solution = Problem12()
    println("Test 1: ${solution.isMatch("aa", "a")}")     // Expected: false
    println("Test 2: ${solution.isMatch("aa", "*")}")     // Expected: true
    println("Test 3: ${solution.isMatch("cb", "?a")}")    // Expected: false
}