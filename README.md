# Problem 1
# LeetCode 1189 - Maximum Number of Balloons

## 📝 Problem Description
Given a string `text`, you want to use the characters of `text` to form as many instances of the word **"balloon"** as possible.

You can use each character in `text` **at most once**. Return the maximum number of instances that can be formed.

---

## 💡 Core Logic & Algorithm (The Bottleneck Concept)
To form a single instance of the word **"balloon"**, we require a specific frequency of characters:
* **'b'** $\rightarrow$ 1 required
* **'a'** $\rightarrow$ 1 required
* **'l'** $\rightarrow$ **2** required
* **'o'** $\rightarrow$ **2** required
* **'n'** $\rightarrow$ 1 required

The maximum number of complete words we can form is determined by the **limiting factor (bottleneck)**—the character that runs out first. Since the letters `'l'` and `'o'` appear twice in the target word, we must divide their total frequencies by 2 (using integer division) before finding the minimum constraint.


# Problem 02: Median of Two Sorted Arrays

## 📝 Problem Description

Given two sorted arrays `nums1` and `nums2` of size `m` and `n` respectively, return the median of the two sorted arrays.

The overall run time complexity must be **$O(\log(m+n))$**.

## 💡 Algorithmic Approach

To achieve the optimal **$O(\log(\min(m, n)))$** time complexity, a **Binary Search on Partitions** approach is utilized instead of merging the two arrays.

1. **Array Size Optimization:** Ensure that the binary search is always performed on the smaller array (`nums1`). This keeps the search space minimal.
2. **Binary Partitioning:** We divide both arrays into a left half and a right half such that:
   * The total number of elements in the combined left halves equals the total elements in the combined right halves.
   * `maxLeftX <= minRightY` and `maxLeftY <= minRightX`
3. **Median Calculation:** * If the combined length $(m + n)$ is **odd**, the median is the maximum element of the left halves: 

$$\max(\text{maxLeftX}, \text{maxLeftY})$$

   * If the combined length is **even**, the median is the average of the maximum of the left halves and the minimum of the right halves: 
   
$$\frac{\max(\text{maxLeftX}, \text{maxLeftY}) + \min(\text{minRightX}, \text{minRightY})}{2.0}$$


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

## 💡 Algorithmic Approach

To avoid exponential time complexity caused by redundant overlapping recursive paths, a **Bottom-Up Dynamic Programming (DP)** approach is utilized. We construct a 2D grid `dp` of size $(M + 1) \times (N + 1)$, where $M$ is the length of string `s` and $N$ is the length of pattern `p`. 

Each cell `dp[i][j]` represents whether the prefix substring `s[0...i-1]` completely matches the prefix pattern `p[0...j-1]`.

1. **Base Case Setup:** * An empty string matches an empty pattern: `dp[0][0] = true`.
   * For patterns containing wildcards that can nullify characters (e.g., `a*` or `a*b*`), we look back 2 spaces in the grid to check for an empty string match: `dp[0][j] = dp[0][j - 2]`.
2. **State Transitions:** For each character pair, we evaluate two main criteria:
   * **Exact/Dot Match:** If `p[j - 1] == s[i - 1]` or `p[j - 1] == '.'`, the state is derived from the diagonal previous state: 
     `dp[i][j] = dp[i - 1][j - 1]`
   * **Asterisk Wildcard Match ('*'):** * **Case 1 (Zero occurrences):** Treat `*` as eliminating its preceding character. We look 2 steps behind in the pattern: `dp[i][j] = dp[i][j - 2]`.
     * **Case 2 (One or more occurrences):** If the character preceding `*` matches the current string character (i.e., `p[j - 2] == s[i - 1]` or `p[j - 2] == '.'`), we retain the previous string matching state: `dp[i][j] = dp[i][j] || dp[i - 1][j]`.

## 📊 Complexity Analysis

* **Time Complexity:** $O(M \times N)$ — Where $M$ and $N$ are the lengths of `s` and `p`. Every cell in the $(M + 1) \times (N + 1)$ matrix is processed exactly once with $O(1)$ operations.
* **Space Complexity:** $O(M \times N)$ — Required matrix allocations to maintain the boolean state table.

