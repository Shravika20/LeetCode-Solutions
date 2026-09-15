/*
Problem: 836. Rectangle Overlap
Difficulty: Easy
Topic: Matrix, Geometry

Approach:
1. Check if the two rectangles overlap in the X direction.
2. Check if they overlap in the Y direction.
3. Both directions must overlap for the intersection
   to have a positive area.

Time Complexity: O(1)
Space Complexity: O(1)
*/

class Solution {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        // Check overlap in X direction
        boolean xOverlap = rec1[0] < rec2[2] &&
                           rec2[0] < rec1[2];

        // Check overlap in Y direction
        boolean yOverlap = rec1[1] < rec2[3] &&
                           rec2[1] < rec1[3];

        return xOverlap && yOverlap;
    }
}