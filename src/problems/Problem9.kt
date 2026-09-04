package problems

class Problem9 {

    fun totalNQueens(n: Int): Int {
        val cols = BooleanArray(n)
        val diag1 = BooleanArray(2 * n)
        val diag2 = BooleanArray(2 * n)

        fun solve(row: Int): Int {
            if (row == n) return 1
            var count = 0
            for (col in 0 until n) {
                val d1 = row - col + n
                val d2 = row + col
                if (cols[col] || diag1[d1] || diag2[d2]) continue

                cols[col] = true; diag1[d1] = true; diag2[d2] = true
                count += solve(row + 1)
                cols[col] = false; diag1[d1] = false; diag2[d2] = false
            }
            return count
        }

        return solve(0)
    }
}

fun main() {
    val p = Problem9()

    println(p.totalNQueens(4)) // Output: 2
    println(p.totalNQueens(1)) // Output: 1
}