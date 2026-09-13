/*
Problem: 835. Image Overlap
Difficulty: Medium
Topic: Matrix, Brute Force, Translation
Approach:
1. Try every possible translation of img1 over img2.
2. For each translation, count how many positions contain 1 in both images.
3. Keep track of the maximum overlap.
4. A translation can be represented by shifting rows and columns.
5. No rotation is performed.
Time Complexity: O(n^4)
Space Complexity: O(1)
*/

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;
        // Try every possible row shift
        for (int rowShift = -(n - 1); rowShift <= n - 1; rowShift++) {
            // Try every possible column shift
            for (int colShift = -(n - 1); colShift <= n - 1; colShift++) {
                int overlap = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int x = i + rowShift;
                        int y = j + colShift;
                        // Check whether the shifted position
                        // is still inside the matrix
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            if (img1[i][j] == 1 && img2[x][y] == 1) {
                                overlap++;
                            }
                        }
                    }
                }
                maxOverlap = Math.max(maxOverlap, overlap);
            }
        }
        return maxOverlap;
    }
}