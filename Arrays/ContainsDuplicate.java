/*
 * Problem: Contains Duplicate
 * Difficulty: Easy
 * Topic: Array / HashSet
 *
 * Approach:
 * Store each element in a HashSet.
 * If an element is already present, a duplicate exists.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

import java.util.HashSet;
import java.util.Scanner;
public class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        boolean result = containsDuplicate(nums);
        System.out.println("Contains Duplicate: " + result);
        sc.close();
    }
}