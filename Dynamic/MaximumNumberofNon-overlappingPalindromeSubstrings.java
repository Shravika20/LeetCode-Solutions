/*
Problem: 2472. Maximum Number of Non-overlapping Palindrome Substrings
Difficulty: Hard
Topic: Dynamic Programming, Palindrome, String
Approach:
1. Create a DP array where dp[i] represents the maximum number
   of valid non-overlapping palindromes using the first i characters.
2. For every ending position, check all possible starting positions.
3. If s[start...end] is a palindrome and its length is at least k,
   we can select it.
4. Then:
       dp[end + 1] = max(dp[end + 1], dp[start] + 1)
5. We also have the option of not selecting a palindrome ending at
   the current position, so:
       dp[i + 1] = max(dp[i + 1], dp[i])
To check whether a substring is a palindrome efficiently, we use
a 2D boolean array:
    palindrome[i][j] = true if s[i...j] is a palindrome.
Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        // palindrome[i][j] = true if s[i...j] is a palindrome
        boolean[][] palindrome = new boolean[n][n];
        // Build palindrome table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || palindrome[i + 1][j - 1])) {
                    palindrome[i][j] = true;
                }
            }
        }
        // dp[i] = maximum number of palindromes
        // using the first i characters
        int[] dp = new int[n + 1];
        for (int end = 0; end < n; end++) {
            // Option 1: Don't select a palindrome ending at end
            dp[end + 1] = dp[end + 1] > dp[end]
                    ? dp[end + 1]
                    : dp[end];
            // Try every possible starting position
            for (int start = 0; start <= end; start++) {
                int length = end - start + 1;
                if (length >= k && palindrome[start][end]) {
                    dp[end + 1] = Math.max(
                        dp[end + 1],
                        dp[start] + 1
                    );
                }
            }
        }
        return dp[n];
    }
}