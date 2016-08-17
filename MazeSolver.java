import java.util.*;
import java.io.*;

public class MazeSolver {

  public static void main(String[] args) throws IOException {
    Scanner f = new Scanner(new File("maze.txt"));
    int numOfLines = 1, numOfColumns = f.nextLine().length();
    while(f.hasNextLine()) {
      f.nextLine();
      numOfLines++;
    }
    char[][] grid = new char[numOfLines][numOfColumns];
    f = new Scanner(new File("maze.txt"));
    for(int i = 0;i < grid.length;i++)
      grid[i] = f.nextLine().toCharArray();
    Node solution = bfs(grid);
    if(solution.distance == -1)
      System.out.println("No path exists");
    else {
      Node current = solution;
      while(current.parent != null) {
        grid[current.row][current.column] = 'X';
        current = current.parent;
      }
      grid[current.row][current.column] = 'X';
      System.out.println("The solution takes " + solution.distance + " steps to complete, and its looks like this:");
      for(int p = 0;p < grid.length;p++) {
        for(int q = 0;q < grid[0].length;q++)
          System.out.print(grid[p][q]);
        System.out.println();
      }
    }
  }

  //input: a 2d array of '.' and '#' elements where a '#' denotes a wall and integers indicating the start and end point
  //output: whether or not the end point is accessible from the start point via 4-direction movement through '.' elements
  public static Node bfs(char[][] grid) {
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int[][] directions = new int[][]{{-1,1,0,0},{0,0,-1,1}};
    Queue<Node> toVisit = new LinkedList<Node>();
    Node start = new Node(0, 1, 1);
    toVisit.add(start);
    while(!toVisit.isEmpty()) {
      Node current = toVisit.poll();
      visited[current.row][current.column] = true;
      for(int i = 0;i < 4;i++) {
        Node n = new Node(current.distance + 1, current, current.row + directions[0][i], current.column + directions[1][i]);
        if(n.row == grid.length - 2 && n.column == grid[0].length - 2) return n;
        if(Math.min(n.row,n.column) >= 0 && n.row < grid.length && n.column < grid[0].length && grid[n.row][n.column] != '#' && !visited[n.row][n.column]) {
          visited[n.row][n.column] = true;
          toVisit.add(n);
        }
      }
    }
    return new Node(-1, -1, -1);
  }
}

class Node {
  int distance, row, column;
  Node parent;

  public Node(int d, int r, int c) {
    row = r;
    column = c;
    distance = d;
  }

  public Node(int d, Node p, int r, int c) {
    row = r;
    column = c;
    distance = d;
    parent = p;
  }
}
