/* 
 * Problem: Minimum Moves to Collect All Litter
 * Difficulty: Medium
 * Topic: BFS, Bitmask, Grid
 * Approach: Use BFS to explore states consisting of the student's position,
 *           remaining energy, and collected litter. Use a bitmask to track
 *           collected litter. Whenever the student reaches a reset area 'R',
 *           restore the energy to its maximum value.
 * Time Complexity: O(m * n * 2^L * energy)
 * Space Complexity: O(m * n * 2^L * energy)
 */
import java.util.*;
class Solution {
    public int minMoves(String[] grid, int energy) {
        int m = grid.length, n = grid[0].length();
        int sr = 0, sc = 0, k = 0;

        int[][] id = new int[m][n];
        for (int[] a : id) Arrays.fill(a, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = grid[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    id[i][j] = k++;
                }
            }
        }
        int full = (1 << k) - 1;
        boolean[][][][] visited =
            new boolean[m][n][1 << k][energy + 1];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc, energy, 0});
        visited[sr][sc][0][energy] = true;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int moves = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                int e = cur[2];
                int mask = cur[3];
                if (mask == full)
                    return moves;
                if (e == 0)
                    continue;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n ||                      grid[nr].charAt(nc) == 'X')
                        continue;
                    int ne = e - 1;
                    int nm = mask;
                    if (grid[nr].charAt(nc) == 'R')
                        ne = energy;
                    if (id[nr][nc] != -1)
                        nm |= 1 << id[nr][nc];
                    if (!visited[nr][nc][nm][ne]) {
                        visited[nr][nc][nm][ne] = true;
                        q.offer(new int[]{nr, nc, ne, nm});
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}