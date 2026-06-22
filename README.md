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
