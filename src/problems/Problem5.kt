package problems

class Solution5 {
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val k = words.getOrNull(0)?.length ?: return emptyList()
        val n = words.size
        val sLen = s.length
        if (sLen < k * n) return emptyList()

        val target = HashMap<String, Int>()
        for (w in words) target[w] = (target[w] ?: 0) + 1

        val result = ArrayList<Int>()

        for (i in 0 until k) {
            var left = i; var right = i; var count = 0
            val cur = HashMap<String, Int>()

            while (right + k <= sLen) {
                val w = s.substring(right, right + k)
                right += k

                val targetCount = target[w] ?: 0
                if (targetCount > 0) {
                    val curCount = (cur[w] ?: 0) + 1
                    cur[w] = curCount
                    count++

                    var limit = curCount
                    while (limit > targetCount) {
                        val lw = s.substring(left, left + k)
                        cur[lw] = (cur[lw] ?: 1) - 1
                        count--
                        left += k
                        if (lw == w) limit--
                    }

                    if (count == n) result.add(left)
                } else {
                    cur.clear()
                    count = 0
                    left = right
                }
            }
        }
        return result
    }
}

// IntelliJ IDEA Runner
fun main() {
    val solution = Solution5()

    // Test Case 1
    val s1 = "barfoothefoobarman"
    val words1 = arrayOf("foo", "bar")
    println("Test 1 Output: ${solution.findSubstring(s1, words1)}") // Output: [0, 9]

    // Test Case 2
    val s2 = "wordgoodgoodgoodbestword"
    val words2 = arrayOf("word", "good", "best", "word")
    println("Test 2 Output: ${solution.findSubstring(s2, words2)}") // Output: []

    // Test Case 3
    val s3 = "barfoofoobarthefoobarman"
    val words3 = arrayOf("bar", "foo", "the")
    println("Test 3 Output: ${solution.findSubstring(s3, words3)}") // Output: [6, 9, 12]
}