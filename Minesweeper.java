//command-line arguments are "<height> <width> <number of mines>"
//note: height must be below 26, width must be below terminal's next line character insertion

import java.util.*;
import static java.lang.System.out;

public class Minesweeper {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int h = Integer.parseInt(args[0]);
    int w = Integer.parseInt(args[1]);
    int nm = Integer.parseInt(args[2]);
    String[][] board = generateBoard(h, w, nm);
    String[][] userBoard = new String[h][w];
    for(int k = 0;k < h;k++) {
      for(int p = 0;p < w;p++) {
        userBoard[k][p] = "?";
      }
    }
    boolean done = false;
    int correct = 0;
    while(!done) {
      out.print("   ");
      for(int ch = 1;ch <= w;ch++) {
        out.print(ch + " ");
      }
      out.println();
      out.print("  ");
      for(int ch = 1;ch <= w;ch++) {
        out.print("--");
      }
      out.println();
      char let = 'a';
      for(int k = 0;k < h;k++) {
        out.print(let++ + "| ");
        for(int p = 0;p < w;p++) {
          out.print(userBoard[k][p] + " ");
        }
        out.println();
      }
      out.println();
      out.println("Dig Somewhere. For example: \"b 2 bomb\" or \"a 1 clean\"");
      args = scan.nextLine().split(" ");
      //need bad input checking code
      userBoard[args[0].charAt(0) - 'a'][Integer.parseInt(args[1]) - 1] =
          board[args[0].charAt(0) - 'a'][Integer.parseInt(args[1]) - 1];
      if(args[2].equals("clear") && board[args[0].charAt(0) - 'a'][Integer.parseInt(args[1]) - 1].equals("M")) {
        out.println("YOU HIT A BOMB. GAME OVER.");
        done = true;
      }
      if(args[2].equals("bomb")) {
        out.println("You marked the spot as a bomb.");
        userBoard[args[0].charAt(0) - 'a'][Integer.parseInt(args[1]) - 1] = "B";
      }
      if(args[2].equals("bomb") && board[args[0].charAt(0) - 'a'][Integer.parseInt(args[1]) - 1].equals("M")) {
        correct++;
      }
      if(correct == nm) {
        out.println("You marked all the bombs! You win!!!");
        done = true;
      }
    }
  }

  public static String[][] generateBoard(int h, int w, int nm) {
    String[][] board = new String[h][w];
    for(int k = 0;k < h;k++) {
      for(int p = 0;p < w;p++) {
        board[k][p] = "0";
      }
    }
    while(nm-- > 0) {
      int r = (int)(Math.random()*h);
      int c = (int)(Math.random()*w);
      if(board[r][c].equals("M")){
        nm++;
        continue;
      }
      board[r][c] = "M";
    }
    for(int i = 0;i < h;i++) {
      for(int j = 0;j < w;j++) {
        if(!board[i][j].equals("M")) {
          int cnt = 0;
          if(i != 0 && board[i - 1][j].equals("M")) cnt++;
          if(j != 0 && board[i][j - 1].equals("M")) cnt++;
          if(i != 0 && j != 0 && board[i - 1][j - 1].equals("M")) cnt++;
          if(i + 1 != h && board[i + 1][j].equals("M")) cnt++;
          if(j + 1 != w && board[i][j + 1].equals("M")) cnt++;
          if(i + 1 != h && j + 1 != w && board[i + 1][j + 1].equals("M")) cnt++;
          if(i != 0 && j + 1 != w && board[i - 1][j + 1].equals("M")) cnt++;
          if(i + 1 != h && j != 0 && board[i + 1][j - 1].equals("M")) cnt++;
          board[i][j] = Integer.toString(cnt);
        }
      }
    }
    return board;
  }

}
