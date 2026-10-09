/*
Problem:1541 Minimum Insertions to Balance a Parentheses String
Difficulty: Medium
Topic: Greedy, String, Intervals
Approach:
1 We'll maintain two variables:
2 open — number of unmatched opening brackets.
insertions — number of brackets we need to insert.
Rules :
1 If we see '(', it needs two ')' characters.
2 If we see ')', check whether the next character is also ')'.
3 If yes, we have a pair '))'.
4 If no, insert one ')' to complete the pair.
5 If there is no unmatched '(' for a closing pair, insert an '('.
6 At the end, each remaining '(' needs two ')' characters.
Time Complexity: O(n)
Space Complexity: O(1)
*/

class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                open++;
                i++;
            } else {
                // Check whether we have two consecutive ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i += 2;
                } else {
                    // Insert a ')' to complete the pair
                    insertions++;
                    i++;
                }

                // If no '(' is available, insert one
                if (open > 0) {
                    open--;
                } else {
                    insertions++;
                }
            }
        }

        // Each remaining '(' needs two ')'
        return insertions + open * 2;
    }
}