/* 
 * Problem: Find Critical Points in a Linked List
 * Difficulty: Medium
 * Topic: Linked List
 * Approach: Traverse the linked list and find local maxima/minima. 
 *           Track the first and previous critical point to calculate
 *           the minimum and maximum distance between critical points.
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstCritical = -1;
        int prevCritical = -1;      
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;       
        ListNode prev = head;
        ListNode curr = head.next;        
        int index = 1;       
        while (curr != null && curr.next != null) {            
            // Check if current node is a critical point
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {              
                // First critical point
                if (firstCritical == -1) {
                    firstCritical = index;
                }
                // Calculate distance from previous critical point
                if (prevCritical != -1) {
                    int distance = index - prevCritical;                  
                    minDistance = Math.min(minDistance, distance);
                    maxDistance = index - firstCritical;
                }   
                prevCritical = index;
            }   
            prev = curr;
            curr = curr.next;
            index++;
        }
        // Fewer than two critical points
        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }       
        return new int[]{minDistance, maxDistance};
    }
}