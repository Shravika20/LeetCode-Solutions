/*
 * Problem: Two Sum
 * Difficulty: Easy
 * Topic: Array / HashMap
 * Approach:
 * Use a HashMap to store previously seen numbers
 * and check whether the required complement exists.
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[] {}; // fallback (required for compilation)
    }
}