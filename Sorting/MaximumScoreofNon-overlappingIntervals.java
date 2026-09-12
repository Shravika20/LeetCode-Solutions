/*
Problem: Maximum Score of Non-overlapping Intervals
Difficulty: Hard
Topic: Dynamic Programming, Binary Search, Sorting

Approach:
1. Sort the intervals by their starting position.
2. For every interval, use binary search to find the next interval
   whose starting position is greater than the current interval's ending
   position. This ensures the intervals do not share any points.
3. Use DP where:
      dp[i][k] = maximum score we can get starting from interval i
                 when we can still choose at most k intervals.
4. At every interval, we have two choices:
      - Skip the current interval.
      - Take the current interval and move to the next non-overlapping one.
5. If two choices have the same score, compare their selected original
   indices lexicographically and keep the smaller one.
6. Since we can choose at most 4 intervals, k is at most 4.

Time Complexity: O(n log n + 4n)
Space Complexity: O(4n)
*/

import java.util.*;
class Solution {
    long[][] dp;
    int[][][] best;
    int[][] intervals;
    int[] next;
    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        intervals = new int[n][4];
        // Store: left, right, weight, original index
        for (int i = 0; i < n; i++) {
            intervals[i][0] = intervalsList.get(i).get(0);
            intervals[i][1] = intervalsList.get(i).get(1);
            intervals[i][2] = intervalsList.get(i).get(2);
            intervals[i][3] = i;
        }

        // Sort by starting position
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        /*
         * next[i] = first interval whose left endpoint
         * is greater than intervals[i]'s right endpoint.
         */
        next = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = findNext(i, n);
        }
        dp = new long[n + 1][5];
        // best[i][k] stores the lexicographically smallest
        // index array among solutions having dp[i][k] score.
        best = new int[n + 1][5][];
        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip current interval
                long skipScore = dp[i + 1][k];
                int[] skipArray = best[i + 1][k];
                // Option 2: Take current interval
                long takeScore = intervals[i][2];
                int[] nextArray = best[next[i]][k - 1];
                if (nextArray != null) {
                    takeScore += dp[next[i]][k - 1];
                }
                int[] takeArray = addIndex(nextArray, intervals[i][3]);
                // Choose the better option
                if (takeScore > skipScore) {
                    dp[i][k] = takeScore;
                    best[i][k] = takeArray;
                } else if (takeScore < skipScore) {
                    dp[i][k] = skipScore;
                    best[i][k] = skipArray;
                } else {
                    // Same score -> lexicographically smaller indices
                    dp[i][k] = takeScore;
                    best[i][k] = lexicographicallySmaller(
                        takeArray,
                        skipArray
                    );
                }
            }
        }
        return best[0][4];
    }
    // Find first interval with left > current right
    private int findNext(int i, int n) {
        int target = intervals[i][1];
        int low = i + 1;
        int high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (intervals[mid][0] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    // Add an original index to the selected array
    private int[] addIndex(int[] arr, int index) {
        int size = arr == null ? 0 : arr.length;
        int[] result = new int[size + 1];
        if (arr != null) {
            System.arraycopy(arr, 0, result, 0, size);
        }
        result[size] = index;
        // We need indices in increasing order
        Arrays.sort(result);
        return result;
    }
    // Return lexicographically smaller array
    private int[] lexicographicallySmaller(int[] a, int[] b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        int len = Math.min(a.length, b.length);
        for (int i = 0; i < len; i++) {
            if (a[i] < b[i]) {
                return a;
            }
            if (a[i] > b[i]) {
                return b;
            }
        }
        // If one is a prefix of the other,
        // shorter array is lexicographically smaller.
        return a.length <= b.length ? a : b;
    }
}