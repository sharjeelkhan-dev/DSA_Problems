package problems

import java.util.PriorityQueue

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution4 {
    fun mergeKLists(lists: Array<ListNode?>?): ListNode? {
        // Base cases
        if (lists == null || lists.isEmpty()) return null

        val minHeap = PriorityQueue<ListNode> { a, b -> a.`val` - b.`val` }

        for (list in lists) {
            if (list != null) {
                minHeap.add(list)
            }
        }

        val dummy = ListNode(0)
        var tail: ListNode? = dummy

        while (!minHeap.isEmpty()) {
            val smallestNode = minHeap.poll()
            tail?.next = smallestNode
            tail = tail?.next

            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next)
            }
        }

        return dummy.next
    }
}

// ==========================================
// Helper functions for testing in IntelliJ
// ==========================================

// Helper to create a Linked List from an IntArray
fun createLinkedList(arr: IntArray): ListNode? {
    if (arr.isEmpty()) return null
    val dummy = ListNode(0)
    var current = dummy
    for (value in arr) {
        current.next = ListNode(value)
        current = current.next!!
    }
    return dummy.next
}

fun linkedListToList(head: ListNode?): List<Int> {
    val result = mutableListOf<Int>()
    var current = head
    while (current != null) {
        result.add(current.`val`)
        current = current.next
    }
    return result
}

fun listsToString(lists: Array<ListNode?>): String {
    return lists.joinToString(", ") { head ->
        linkedListToList(head).toString()
    }
}


fun main() {
    val solution = Solution4()

    val testCases = listOf(
        Pair(
            arrayOf(
                createLinkedList(intArrayOf(1, 4, 5)),
                createLinkedList(intArrayOf(1, 3, 4)),
                createLinkedList(intArrayOf(2, 6))
            ),
            listOf(1, 1, 2, 3, 4, 4, 5, 6)
        ),
        Pair(
            emptyArray<ListNode?>(),
            emptyList()
        ),
        Pair(
            arrayOf<ListNode?>(null),
            emptyList()
        ),
        Pair(
            arrayOf(
                null,
                createLinkedList(intArrayOf(1)),
                null,
                createLinkedList(intArrayOf(0))
            ),
            listOf(0, 1)
        )
    )

    println("==============================================")
    println("      Merge K Sorted Lists Test Results       ")
    println("==============================================")

    for (i in testCases.indices) {
        val (listsInput, expected) = testCases[i]

        val inputAsString = listsToString(listsInput)

        // Execute algorithm
        val mergedHead = solution.mergeKLists(listsInput)
        val actualResult = linkedListToList(mergedHead)

        val status = if (actualResult == expected) "PASSED" else "FAILED"

        println("Test Case ${i + 1}:")
        println("Input Lists   : [$inputAsString]")
        println("Expected      : $expected")
        println("Actual Result : $actualResult -> $status")
        println("-".repeat(46))
    }
}