package problems

import kotlin.math.max
import kotlin.math.min

class Solution2 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        if (nums1.size > nums2.size) {
            return findMedianSortedArrays(nums2, nums1)
        }

        val m = nums1.size
        val n = nums2.size
        var low = 0
        var high = m

        while (low <= high) {
            val partitionX = (low + high) / 2
            val partitionY = (m + n + 1) / 2 - partitionX

            val maxLeftX = if (partitionX == 0) Int.MIN_VALUE else nums1[partitionX - 1]
            val minRightX = if (partitionX == m) Int.MAX_VALUE else nums1[partitionX]

            val maxLeftY = if (partitionY == 0) Int.MIN_VALUE else nums2[partitionY - 1]
            val minRightY = if (partitionY == n) Int.MAX_VALUE else nums2[partitionY]

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((m + n) % 2 != 0) {
                    return max(maxLeftX, maxLeftY).toDouble()
                }
                else {
                    return (max(maxLeftX, maxLeftY) + min(minRightX, minRightY)) / 2.0
                }
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1
            } else {
                low = partitionX + 1
            }
        }

        throw IllegalArgumentException("Input arrays are not sorted or invalid.")
    }
}
fun main() {
    val solution = Solution2()

    // Test Case 1
    val nums1 = intArrayOf(1, 3)
    val nums2 = intArrayOf(2)
    val median1 = solution.findMedianSortedArrays(nums1, nums2)
    println("Test Case 1 Median: $median1")

    // Test Case 2
    val nums3 = intArrayOf(1, 2)
    val nums4 = intArrayOf(3, 4)
    val median2 = solution.findMedianSortedArrays(nums3, nums4)
    println("Test Case 2 Median: $median2")
}