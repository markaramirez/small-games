import java.util.*;
import java.io.*;

public class MazeGenerator {

  public static void main(String[] args) throws IOException {
    PrintWriter w = new PrintWriter("maze.txt", "UTF-8");
    char[][] grid = new char[(int)(Math.random()*100)+1][(int)(Math.random()*100)+1];
    boolean maze = false;
  	while(!maze) {
      for(int i = 0;i < grid.length;i++) {
      	for(int j = 0;j < grid[0].length;j++)
        	grid[i][j] = (Math.random()*2 > 1.25)?'#':'.';
      }
      maze = bfs(grid, 0, 0, grid.length - 1, grid[0].length - 1);
  	}
  	for(int i = 0;i < grid[0].length + 2;i++) w.print("#");
    w.println();
    for(int i = 0;i < grid.length;i++) {
      w.print("#");
      for(int j = 0;j < grid[0].length;j++)
        w.print(grid[i][j]);
      w.print("#");
      w.println();
    }
	  for(int i = 0;i < grid[0].length + 2;i++) w.print("#");
    w.println();
    w.close();
  }

  //input: a 2d array of '.' and '#' elements where a '#' denotes a wall and integers indicating the start and end point
  //output: whether or not the end point is accessible from the start point via 4-direction movement through '.' elements
  public static boolean bfs(char[][] grid, int rowStart, int columnStart, int rowEnd, int columnEnd) {
    grid[rowStart][columnStart] = 's';
    grid[rowEnd][columnEnd] = 'e';
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int[][] directions = new int[][]{{-1,1,0,0},{0,0,-1,1}};
    Queue<Integer> toVisit = new LinkedList<Integer>();
    toVisit.add(rowStart);
    toVisit.add(columnStart);
    while(!toVisit.isEmpty()) {
      int r = toVisit.poll();
      int c = toVisit.poll();
      visited[r][c] = true;
      for(int i = 0;i < 4;i++) {
        int nr = r + directions[0][i];
        int nc = c + directions[1][i];
        if(Math.min(nr,nc) >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc] != '#' && !visited[nr][nc]) {
          visited[nr][nc] = true;
          toVisit.add(nr);
          toVisit.add(nc);
        }
      }
    }
    if(visited[rowEnd][columnEnd])
      return true;
    else
      return false;
  }
}
