# Problem 01: LeetCode 1189 - Maximum Number of Balloons

## 📝 Problem Description
Given a string `text`, you want to use the characters of `text` to form as many instances of the word **"balloon"** as possible.

You can use each character in `text` **at most once**. Return the maximum number of instances that can be formed.

---

## 💡 Algorithmic Approach (The Bottleneck Concept)
To form a single instance of the word **"balloon"**, we require a specific frequency of characters:
* **'b'** $\rightarrow$ 1 required
* **'a'** $\rightarrow$ 1 required
* **'l'** $\rightarrow$ **2** required
* **'o'** $\rightarrow$ **2** required
* **'n'** $\rightarrow$ 1 required

The maximum number of complete words we can form is determined by the **limiting factor (bottleneck)**—the character that runs out first. Since the letters `'l'` and `'o'` appear twice in the target word, we must divide their total frequencies by 2 (using integer division) before finding the minimum constraint.

---

## 📊 Complexity Analysis

* **Time Complexity:** $O(N)$ — Where $N$ is the length of the string `text`. We iterate through the string once to count character frequencies.
* **Space Complexity:** $O(1)$ — A fixed-size array or hash map (size 26 for English alphabet) is used to store frequencies, requiring constant space.

---

# Problem 02: LeetCode 4 - Median of Two Sorted Arrays

## 📝 Problem Description

Given two sorted arrays `nums1` and `nums2` of size `m` and `n` respectively, return the median of the two sorted arrays.

The overall run time complexity must be **$O(\log(m+n))$**.

---

## 💡 Algorithmic Approach

To achieve the optimal **$O(\log(\min(m, n)))$** time complexity, a **Binary Search on Partitions** approach is utilized instead of merging the two arrays.

1. **Array Size Optimization:** Ensure that the binary search is always performed on the smaller array (`nums1`). This keeps the search space minimal.
2. **Binary Partitioning:** We divide both arrays into a left half and a right half such that:
   * The total number of elements in the combined left halves equals the total elements in the combined right halves.
   * `maxLeftX <= minRightY` and `maxLeftY <= minRightX`
3. **Median Calculation:** 
   * If the combined length $(m + n)$ is **odd**, the median is the maximum element of the left halves: 

$$\max(\text{maxLeftX}, \text{maxLeftY})$$

   * If the combined length is **even**, the median is the average of the maximum of the left halves and the minimum of the right halves: 

$$\frac{\max(\text{maxLeftX}, \text{maxLeftY}) + \min(\text{minRightX}, \text{minRightY})}{2.0}$$

---

## 📊 Complexity Analysis

* **Time Complexity:** $O(\log(\min(M, N)))$ — Where $M$ and $N$ are the lengths of `nums1` and `nums2`. Binary search is applied strictly on the smaller array to optimize step count.
* **Space Complexity:** $O(1)$ — Partition evaluation is performed in-place using pointers without extra memory allocations.

---

# Problem 03: LeetCode 10 - Regular Expression Matching

## 📝 Problem Description

Given an input string `s` and a pattern `p`, implement regular expression matching with support for `.` and `*` where:
* `.` Matches any single character.
* `*` Matches zero or more of the preceding element.

Return a boolean indicating whether the matching covers the **entire** input string (not partial).

### Constraints
* `1 <= s.length <= 20`
* `1 <= p.length <= 20`
* `s` contains only lowercase English letters.
* `p` contains only lowercase English letters, `.`, and `*`.
* It is guaranteed for each appearance of the character `*`, there will be a previous valid character to match.

---

## 💡 Algorithmic Approach

To avoid exponential time complexity caused by redundant overlapping recursive paths, a **Bottom-Up Dynamic Programming (DP)** approach is utilized. We construct a 2D grid `dp` of size $(M + 1) \times (N + 1)$, where $M$ is the length of string `s` and $N$ is the length of pattern `p`. 

Each cell `dp[i][j]` represents whether the prefix substring `s[0...i-1]` completely matches the prefix pattern `p[0...j-1]`.

1. **Base Case Setup:** 
   * An empty string matches an empty pattern: `dp[0][0] = true`.
   * For patterns containing wildcards that can nullify characters (e.g., `a*` or `a*b*`), we look back 2 spaces in the grid to check for an empty string match: `dp[0][j] = dp[0][j - 2]`.
2. **State Transitions:** For each character pair, we evaluate two main criteria:
   * **Exact/Dot Match:** If `p[j - 1] == s[i - 1]` or `p[j - 1] == '.'`, the state is derived from the diagonal previous state: 
     `dp[i][j] = dp[i - 1][j - 1]`
   * **Asterisk Wildcard Match ('*'):** 
     * **Case 1 (Zero occurrences):** Treat `*` as eliminating its preceding character. We look 2 steps behind in the pattern: `dp[i][j] = dp[i][j - 2]`.
     * **Case 2 (One or more occurrences):** If the character preceding `*` matches the current string character (i.e., `p[j - 2] == s[i - 1]` or `p[j - 2] == '.'`), we retain the previous string matching state: `dp[i][j] = dp[i][j] || dp[i - 1][j]`.

---

## 📊 Complexity Analysis

* **Time Complexity:** $O(M \times N)$ — Where $M$ and $N$ are the lengths of `s` and `p`. Every cell in the matrix is evaluated once in constant $O(1)$ time.
* **Space Complexity:** $O(M \times N)$ — 2D array allocated to store matching states across string and pattern prefixes.

---

# Problem 04: LeetCode 23 - Merge k Sorted Lists

## 📝 Problem Description
You are given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order. Merge all the linked-lists into one sorted linked-list and return it.

### Constraints
* `k == lists.length`
* `0 <= k <= 10^4`
* `0 <= lists[i].length <= 500`
* `-10^4 <= lists[i][j] <= 10^4`
* `lists[i]` is sorted in ascending order.
* The sum of `lists[i].length` will not exceed `10^4`.

---

## 💡 Algorithmic Approach
To merge multiple sorted lists optimally without redundant traversals, a **Min-Heap (Priority Queue)** approach is utilized. This allows us to dynamically fetch the minimum node across all active heads in $O(\log k)$ time.

1. **Heap Initialization:** We initialize a Min-Heap (Priority Queue) comparing the integer values (`val`) of the `ListNode` elements:
   $$a.val - b.val$$
2. **First-Element Ingestion:** We add the head node of each of the $k$ lists into the Min-Heap (safely ignoring any `null` or empty lists).
3. **Iterative Extraction and Re-insertion:**
   * Extract (poll) the smallest node from the heap.
   * Attach this smallest node to our running merged list's `tail`.
   * If the extracted node has a valid `next` node in its original list, we push that next node back into the Min-Heap to keep the heap dynamically balanced.
4. **Dummy Head Pointer:** A dummy node is initialized at the start to easily build and return the head of our newly sorted, merged linked list (`dummy.next`).

---

## 📊 Complexity Analysis
* **Time Complexity:** $O(N \log k)$ — Where $N$ is the total number of nodes across all $k$ linked lists. Each node is added and removed from the priority queue once, taking $O(\log k)$ time per insertion/extraction.
* **Space Complexity:** $O(k)$ — The min-heap stores at most $k$ elements at any given point (one representative head node from each list).

---

# Problem 05: LeetCode 30 - Substring with Concatenation of All Words

## 📝 Problem Description
You are given a string `s` and an array of strings `words`. All the strings of `words` are of the **same length**.

A **concatenated string** is a string that exactly contains all the strings of any permutation of `words` concatenated.

Return an array of the starting indices of all the concatenated substrings in `s`. You can return the answer in **any order**.

### Constraints
* `1 <= s.length <= 10^4`
* `1 <= words.length <= 5000`
* `1 <= words[i].length <= 30`
* `s` and `words[i]` consist of lowercase English letters.

---

## 💡 Algorithmic Approach

To avoid the exponential overhead of checking string permutations, a **Fixed-Step Sliding Window** technique combined with **Frequency Hash Maps** is used.

1. **Alignment Offsets:** Since every word in `words` has an identical length $K$, there are only $K$ distinct starting offsets ($0 \text{ to } K - 1$). Running a separate sliding window pass for each offset guarantees that every valid word alignment is checked without overlapping redundant computations.
2. **Frequency Map Comparison:**
   * Build a `target` frequency map storing the expected count of each word in `words`.
   * For each offset pass, maintain a `cur` frequency map tracking word counts within the current sliding window.
3. **Dynamic Window Adjustment:**
   * Slide the window rightward by stepping $K$ characters at a time.
   * If an extracted word exists in `target`, increment its count in `cur`.
   * If a word's count exceeds its allowed target frequency, advance the `left` pointer in steps of $K$ to shrink the window until the frequency is balanced.
   * If a word is not present in `target`, clear the current window state and reset `left = right`.
4. **Result Capture:** When the window contains exactly $N$ valid words (`count == N`), the starting index `left` is recorded.

---

## 📊 Complexity Analysis

* **Time Complexity:** $O(|S| \times K)$ — Where $|S|$ is the length of string s and $K$ is the length of an individual word in words. There are $K$ offset iterations, and in each pass, every $K$-length substring chunk enters and exits the window at most once.
* **Space Complexity:** $O(N \times K)$ — Where $N$ is the total count of words in words and $K$ is the word length. Memory is allocated for storing word frequencies in hash maps (target and cur).

---

# Problem 06: LeetCode 17 - Letter Combinations of a Phone Number

## 📝 Problem Description  
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

2: a, b, c
3: d, e, f
4: g, h, i
5: j, k, l
6: m, n, o
7: p, q, r, s
8: t, u, v
9: w, x, y, z

### Constraints
* `0 <= digits.length <= 4`
* `digits[i]` is a digit in the range `['2', '9']`.

---

## 💡 Algorithmic Approach
To generate all possible permutations without redundant computations, a Backtracking (Depth-First Search) pattern is utilized.

1. **Mapping Setup:** Store telephone keypad character mappings using an indexed array or hash map for $O(1)$ lookups.
2. **Recursive Exploration:**
   * Maintain a pointer index tracking the current target character in the digits string.
   * Maintain a mutable string buffer (`StringBuilder`) to construct individual combinations sequentially.
3. **Base Case:** When `index == digits.length`, the current path represents a valid full combination. Convert the buffer to a string and store it in the output list.

---

## 📊 Complexity Analysis

* **Time Complexity:** $O(4^N \times N)$ — Where $N$ is the length of digits. In the worst case (digits '7' or '9'), each digit expands into 4 candidate letters, yielding up to $4^N$ combinations. Constructing each string takes $O(N)$ time.
* **Space Complexity:** $O(N)$ — Where $N$ is the length of digits. The space is consumed by the recursion call stack and mutable string buffer up to a max depth of $N$ (excluding output list memory).

---

# Problem 07: LeetCode 24 - Swap Nodes in Pairs

## 📝 Problem Description  
Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed).

### Constraints
* The number of nodes in the list is in the range `[0, 100]`.
* `0 <= Node.val <= 100`.

---

## 💡 Algorithmic Approach
To swap adjacent nodes without altering internal node values, we reorder the node pointer references iteratively using a Dummy Node pattern.

1. **Dummy Node Setup:** Initialize a dummy node pointing to head and maintain a pointer `current` initialized to `dummy`. This gracefully handles boundary conditions, such as swapping the actual head node.
2. **Pointer Swapping:** Traverse while `current.next` and `current.next.next` are both non-null.
   * Identify `first = current.next` and `second = current.next.next`.
   * Reassign references:
     * `first.next = second.next`
     * `second.next = first`
     * `current.next = second`
3. **Pointer Advancement:** Advance `current` two nodes forward to `first` for the next iteration step.

---

## 📊 Complexity Analysis

* **Time Complexity:** $O(N)$ — Where $N$ is the total number of nodes in the linked list. Every node reference is updated in a single pass.
* **Space Complexity:** $O(1)$ — Pointer adjustments are performed in-place using constant auxiliary memory.

---

# Problem 08: LeetCode 8 - String to Integer (atoi)

## 📝 Problem Description
Implement the `myAtoi(string s)` function, which converts a string to a 32-bit signed integer.

The algorithm for `myAtoi(string s)` is as follows:
1. **Whitespace:** Ignore any leading whitespace (`" "`).
2. **Signedness:** Determine the sign by checking if the next character is `'-'` or `'+'`. Assuming positivity if neither is present.
3. **Conversion:** Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, the result is `0`.
4. **Rounding:** If the integer is out of the 32-bit signed integer range $[-2^{31}, 2^{31} - 1]$, clamp the integer so that it remains in the range. Specifically, integers less than $-2^{31}$ should be clamped to $-2^{31}$, and integers greater than $2^{31} - 1$ should be clamped to $2^{31} - 1$.

### Constraints
* `0 <= s.length <= 200`
* `s` consists of English letters (lower-case and upper-case), digits (`0-9`), `' '`, `'+'`, `'-'`, and `'.'`.

---

## 💡 Algorithmic Approach

To process the string predictably and adhere strictly to 32-bit integer boundaries, a sequential functional transformation pipeline is used:

1. **Trimming Leading Spaces:** Remove leading whitespace using `trimStart()`. If the trimmed string is empty, return `0` immediately.
2. **Sign Extraction:** Inspect the first character to determine if the multiplier is negative (`-1`) or positive (`1`), and adjust the starting index for digit parsing accordingly.
3. **Sequential Digit Accumulation:** 
   * Extract contiguous digits using `takeWhile { it.isDigit() }`.
   * Accumulate digits using a 64-bit integer (`Long`) accumulator: `acc * 10 + (ch - '0')`.
4. **Overflow and Boundary Clamping:** Clamp intermediate calculations against 32-bit signed limits (`Int.MAX_VALUE` and `Int.MIN_VALUE`) using `.coerceAtMost()` before applying sign adjustment and casting back to a 32-bit signed integer (`Int`).

---

## 📊 Complexity Analysis

* **Time Complexity:**  $O(N)$ — Where $N$ is the length of the string s. Trimming, filtering continuous digit characters, and arithmetic reduction each require a single linear pass.
* **Space Complexity:** $O(N)$ — In-memory string operations (trimStart, substring, and digit array extractions) create auxiliary string segments proportional to string length $N$.

---


# Problem 09: LeetCode 52 - N-Queens II

## 📝 Problem Description
The n-queens puzzle is the problem of placing $n$ queens on an $n \times n$ chessboard such that no two queens attack each other.Given an integer $n$, return the number of distinct solutions to the n-queens puzzle.

### Constraints
1 <= n <= 9

---

## 💡 Algorithmic Approach

To determine the total number of distinct valid queen placements without generating full board representations, a Backtracking approach with $O(1)$ state tracking is utilized.

1. Row-by-Row Placement: Place exactly one queen per row (from row = 0 to n - 1), automatically eliminating horizontal row conflicts.
2. $O(1)$ Attack Checking: Maintain three boolean tracking arrays to check whether placing a queen at cell (row, col) is safe in constant time:
Columns: Tracked by index col.
Main Diagonals ($\backslash$): Tracked by index row - col + n.
Anti-Diagonals ($/$): Tracked by index row + col.

3. Recursive Exploration: Recursively attempt placement row-by-row. When row == n, a complete valid placement is reached, incrementing the solution counter.
4. Clean Scope: Encapsulating the backtracking within a local helper function avoids polluting class-level state and eliminates redundant function parameters.

---

## 📊 Complexity Analysis

* **Time Complexity:**   $O(N!)$ — For the first row, there are $N$ choices, for the second at most $N-2$, and so on, bounded by $O(N!)$.
* **Space Complexity:**  $O(N)$ — Memory is consumed by the recursion stack and the three boolean tracking arrays of size $O(N)$.

---

# Problem 10: LeetCode 45 - Jump Game II

## 📝 Problem Description
You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:

0 <= j <= nums[i]  
i + j < n
Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.

### Constraints
1 <= nums.length <= 10^4
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n-1].

---

## 💡 Algorithmic Approach

To find the minimum number of jumps required to reach the last index, a Greedy Algorithm (equivalent to implicit Breadth-First Search) is used:

1. Range Boundary Tracking:
Instead of trying every combination of jumps recursively, we maintain the maximum reachable boundary (farthest) for all indices within the current jump range (currentEnd).

2. Iterative Range Expansion:
Traverse the array from i = 0 up to n - 2 (since reaching n - 1 means we have completed our target).

At each index i, calculate the furthest reachable point: farthest = max(farthest, i + nums[i]).

3. Jump Triggering:
When index i reaches currentEnd, it indicates that all options within the current jump range have been explored.
We must commit to a jump: increment jumps by 1 and update currentEnd = farthest.
If currentEnd reaches or exceeds n - 1, we can break early as the target is guaranteed to be reached.

---

## 📊 Complexity Analysis

* **Time Complexity:**  $O(N)$ — Where $N$ is the length of nums. The array is traversed in a single linear pass.
* **Space Complexity:** $O(1)$ — The algorithm uses constant auxiliary memory (jumps, currentEnd, farthest).

---
