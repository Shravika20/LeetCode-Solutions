/*
 * Problem:Number of Closed Islands.
 * Difficulty: Medium
 * Topic:  Arrays
 * Approach: 1. Visit all boundary cells. Whenever a boundary cell contains 0, use DFS to change its entire connected island to 1.
2. Scan the inner cells.
3. Whenever an unvisited 0 remains, it belongs to a closed island. Run DFS to mark it and increment the count.
 * Time Complexity: O(m * n)
 * Space Complexity: O(m * n)
 */

class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Remove all islands connected to the boundary.
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }

        int count = 0;

        // Count the remaining islands.
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;

        if (r < 0 || c < 0 || r >= m || c >= n ||
            grid[r][c] == 1) {
            return;
        }

        grid[r][c] = 1; // Mark visited.

        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}