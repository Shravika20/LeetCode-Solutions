/*
Problem: 1477. Find Two Non-overlapping Sub-arrays Each With Target Sum
Difficulty: Medium
Topic: Prefix Sum, HashMap, Dynamic Programming
Approach:
1. Use prefix sum to find sub-arrays whose sum is equal to target.
2. For every position, store the minimum length of a valid sub-array
   that ends before the current position.
3. When we find a new sub-array [left ... right] with sum = target,
   check whether there was a valid sub-array before left.
4. If so, combine their lengths and update the minimum answer.
5. A DP array is used to remember the shortest valid sub-array found
   up to each position.
Time Complexity: O(n)
Space Complexity: O(n)
*/

import java.util.*;
class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // best[i] = minimum length of a valid sub-array
        // completely inside indices [0 ... i]
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);
        // prefixSum -> latest index
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int prefixSum = 0;
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            prefixSum += arr[i];
            // Copy the previous best answer
            if (i > 0) {
                best[i] = best[i - 1];
            }
            // We need:
            // prefixSum - previousPrefix = target
            int required = prefixSum - target;
            if (map.containsKey(required)) {
                int left = map.get(required);
                // Sub-array is (left ... i)
                int length = i - left;
                // Check if another valid sub-array
                // exists completely before this one
                if (left >= 0 && best[left] != Integer.MAX_VALUE) {
                    answer = Math.min(
                        answer,
                        length + best[left]
                    );
                }
                // This sub-array is the best valid sub-array
                // ending at or before i
                best[i] = Math.min(best[i], length);
            }
            // Store current prefix sum
            map.put(prefixSum, i);
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}