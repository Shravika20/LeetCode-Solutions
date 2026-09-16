/*
Problem: 1621. Number of Sets of K Non-Overlapping Line Segments
Difficulty: Medium
Topic: Dynamic Programming
Approach:
1. Use DP to count ways to draw exactly k non-overlapping segments.
2. dp[i][j] represents the number of ways to draw j segments
   using points from 0 to i.
3. Use a prefix sum to efficiently count possible starting points.
4. Calculate the answer modulo 1,000,000,007.
Time Complexity: O(n * k)
Space Complexity: O(n * k)
*/

class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        // dp[i][j] = ways to draw j segments using points 0...i
        long[][] dp = new long[n][k + 1];
        // With 0 segments, there is always 1 way
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= k; j++) {
            long prefix = 0;
            for (int i = 1; i < n; i++) {
                // Add ways from previous number of segments
                prefix = (prefix + dp[i - 1][j - 1]) % MOD;
                // Skip point i or use it as the end of a segment
                dp[i][j] = (dp[i - 1][j] + prefix) % MOD;
            }
        }
        return (int) dp[n - 1][k];
    }
}