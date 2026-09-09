package problems

class Problem11 {

    fun minDistance(word1: String, word2: String): Int {
        val m = word1.length
        val n = word2.length
        val dp = Array(m + 1) { i -> IntArray(n + 1) { j -> if (i == 0) j else if (j == 0) i else 0 } }

        for (i in 1..m) {
            for (j in 1..n) {
                dp[i][j] = if (word1[i - 1] == word2[j - 1]) dp[i - 1][j - 1]
                else 1 + minOf(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1])
            }
        }
        return dp[m][n]
    }
}

fun main() {
    val p = Problem11()

    println(p.minDistance("horse", "ros"))            // Output: 3
    println(p.minDistance("intention", "execution"))  // Output: 5
}