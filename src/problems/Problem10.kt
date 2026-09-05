package problems

class Problem10 {

    fun jump(nums: IntArray): Int {
        val n = nums.size
        if (n <= 1) return 0

        var jumps = 0
        var currentEnd = 0
        var farthest = 0

        for (i in 0 until n - 1) {
            farthest = maxOf(farthest, i + nums[i])

            if (i == currentEnd) {
                jumps++
                currentEnd = farthest

                if (currentEnd >= n - 1) break
            }
        }

        return jumps
    }
}

fun main() {
    val p = Problem10()

    println(p.jump(intArrayOf(2, 3, 1, 1, 4))) // Output: 2
    println(p.jump(intArrayOf(2, 3, 0, 1, 4))) // Output: 2
}