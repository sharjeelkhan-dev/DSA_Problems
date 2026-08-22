package problems

class Problem7 {

    fun swapPairs(head: ListNode?): ListNode? {
        val dummy = ListNode(0).apply { next = head }
        var current: ListNode? = dummy

        while (current?.next?.next != null) {
            val first = current.next
            val second = current.next?.next

            first?.next = second?.next
            second?.next = first
            current.next = second

            current = first
        }

        return dummy.next
    }
}

fun IntArray.toListNode(): ListNode? = foldRight(null as ListNode?) { value, nextNode ->
    ListNode(value).apply { next = nextNode }
}

// Extension function to convert ListNode to Kotlin List for printing
fun ListNode?.toKotlinList(): List<Int> = generateSequence(this) { it.next }.map { it.`val` }.toList()

fun main() {
    val p = Problem7()

    println(p.swapPairs(intArrayOf(1, 2, 3, 4).toListNode()).toKotlinList())
    println(p.swapPairs(intArrayOf().toListNode()).toKotlinList())
    println(p.swapPairs(intArrayOf(1).toListNode()).toKotlinList())
    println(p.swapPairs(intArrayOf(1, 2, 3).toListNode()).toKotlinList())
}