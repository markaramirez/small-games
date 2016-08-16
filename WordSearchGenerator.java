public class WordSearchGenerator {

  public static void main(String[] args) {
    char[][] grid = new char[25][25];
    String[] words = {"EEVEE", "PSYDUCK", "SCYTHER", "DODRIO", "CHARMANDER", "PIKACHU", "SQUIRTLE", "RAPIDASH", "GEODUDE", "RHYDON"};
    for(int r = 0;r < 25;r++) {
      for(int c = 0;c < 25;c++)
        grid[r][c] = (char)(65 + (int)(Math.random()*26));
    }
    boolean[][] used = new boolean[25][25];
    for(int i = 0;i < words.length;i++) {
      int rr = (int)(Math.random()*25);
      int rc = (int)(Math.random()*25);
      int d = (int)(Math.random()*8);
      if(used[rr][rc]) {
        i--;
        continue;
      }
      boolean broke = false;
      if(d == 0) { //E----------------------------------------------------
        for(int j = 0;j < words[i].length();j++) {
          if(rc + j >= 25 || used[rr][rc + j])  {
            broke = true;
            break;
          }
        }
        if(!broke) {
          for(int j = 0;j < words[i].length();j++) {
          	grid[rr][rc + j] = words[i].charAt(j);
         	  used[rr][rc + j] = true;
          }
        }
      }
      if(d == 1) { //W----------------------------------------------------
        for(int j = 0;j < words[i].length();j++) {
          if(rc - j < 0 || used[rr][rc - j])  {
            broke = true;
            break;
          }
        }
        if(!broke) {
          for(int j = 0;j < words[i].length();j++) {
          	grid[rr][rc - j] = words[i].charAt(j);
         	  used[rr][rc - j] = true;
          }
        }
      }
      if(d == 2) { //N----------------------------------------------------
        for(int j = 0;j < words[i].length();j++) {
          if(rr - j < 0 || used[rr - j][rc])  {
            broke = true;
            break;
          }
        }
        if(!broke) {
          for(int j = 0;j < words[i].length();j++) {
          	grid[rr - j][rc] = words[i].charAt(j);
         	  used[rr - j][rc] = true;
          }
        }
      }
      if(d == 3) { //S----------------------------------------------------
        for(int j = 0;j < words[i].length();j++) {
          if(rr + j >= 25 || used[rr + j][rc])  {
            broke = true;
            break;
          }
        }
        if(!broke) {
          for(int j = 0;j < words[i].length();j++) {
          	grid[rr + j][rc] = words[i].charAt(j);
         	  used[rr + j][rc] = true;
          }
        }
      }
      if(d == 4) { //NE----------------------------------------------------
        for(int j = 0;j < words[i].length();j++) {
          if(rr - j < 0 || rc + j >= 25 || used[rr - j][rc + j])  {
            broke = true;
            break;
          }
        }
        if(!broke) {
          for(int j = 0;j < words[i].length();j++) {
          	grid[rr - j][rc + j] = words[i].charAt(j);
         	  used[rr - j][rc + j] = true;
          }
        }
      }
      if(d == 5) { //SE----------------------------------------------------
        for(int j = 0;j < words[i].length();j++) {
          if(rr + j >= 25 || rc + j >= 25 || used[rr + j][rc + j])  {
            broke = true;
            break;
          }
        }
        if(!broke) {
          for(int j = 0;j < words[i].length();j++) {
          	grid[rr + j][rc + j] = words[i].charAt(j);
         	  used[rr + j][rc + j] = true;
          }
        }
      }
      if(d == 6) { //SW----------------------------------------------------
        for(int j = 0;j < words[i].length();j++) {
          if(rr + j >= 25 || rc - j < 0 || used[rr + j][rc - j])  {
            broke = true;
            break;
          }
        }
        if(!broke) {
          for(int j = 0;j < words[i].length();j++) {
          	grid[rr + j][rc - j] = words[i].charAt(j);
         	  used[rr + j][rc - j] = true;
          }
        }
      }
      if(d == 7) { //NW----------------------------------------------------
        for(int j = 0;j < words[i].length();j++) {
          if(rr - j < 0 || rc - j < 0 || used[rr - j][rc - j])  {
            broke = true;
            break;
          }
        }
        if(!broke) {
          for(int j = 0;j < words[i].length();j++) {
          	grid[rr - j][rc - j] = words[i].charAt(j);
         	  used[rr - j][rc - j] = true;
          }
        }
      }
      if(broke) {
        i--;
        continue;
      }
    }
    for(int r = 0;r < 25;r++) {
      for(int c = 0;c < 25;c++) {
        System.out.print(grid[r][c] + " ");
      }
      System.out.println();
    }
    for(int i = 0;i < words.length;i+=3) {
      System.out.print(words[i] + "  ");
      if(i+1 >= words.length) break;
      System.out.print(words[i+1] + "  ");
      if(i+2 >= words.length) break;
      System.out.print(words[i+2] + "  ");
      System.out.println();
    }
  }
}
