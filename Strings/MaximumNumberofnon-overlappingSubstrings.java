/*
Problem: 1520. Maximum Number of Non-Overlapping Substrings
Difficulty: Hard
Topic: Greedy, String, Intervals

Approach:
1. Find the first and last occurrence of every character.
2. For each character, try to create the smallest valid substring
   starting from its first occurrence.
3. While expanding the substring, if we find a character whose first
   occurrence is before our current start, this substring is invalid.
4. Otherwise, extend the right boundary to include all occurrences
   of every character inside the substring.
5. We now have all possible smallest valid substrings.
6. Select non-overlapping substrings greedily by their ending position.
7. If two valid choices give the same maximum number of substrings,
   choosing the earliest-ending intervals also gives the minimum
   total length.
Time Complexity: O(n)
Space Complexity: O(n)
*/

import java.util.*;
class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, n);
        Arrays.fill(last, -1);
        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }
        // Store valid intervals [left, right]
        List<int[]> intervals = new ArrayList<>();
        // Try to create a valid substring for every character
        for (int c = 0; c < 26; c++) {
            if (first[c] == n) {
                continue;
            }
            int left = first[c];
            int right = last[c];
            boolean valid = true;
            for (int i = left; i <= right; i++) {
                int current = s.charAt(i) - 'a';
                // This character occurs before our left boundary,
                // so we cannot include all of its occurrences.
                if (first[current] < left) {
                    valid = false;
                    break;
                }
                // Need to include all occurrences of this character
                right = Math.max(right, last[current]);
            }
            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }
        // Sort by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));
        List<String> result = new ArrayList<>();
        int previousEnd = -1;
        // Greedily choose the earliest-ending valid substring
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            if (left > previousEnd) {
                result.add(s.substring(left, right + 1));
                previousEnd = right;
            }
        }
        return result;
    }
}