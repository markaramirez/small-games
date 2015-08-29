import java.util.*;
import java.io.*;

public class WPM {

    public static void main(String[] args) throws IOException {
       Scanner scan = new Scanner(System.in);
       Scanner list = new Scanner(new File("common.txt"));
       String[] nouns = new String[100];
       String[] coms = {"You're on fire!!", "Good Job!", "Great!", "Keep it up!", "Nice Going!", "You got it!", "You're a Master!", "Nice Work!", "Excellent.", "Outstanding.", "There are skid-marks on the keyboard!!!"};
       for(int i = 0;i < 100;i++) {
       		nouns[i] = list.nextLine().substring(8);
       }
       System.out.println("TYPE!");
  	   long t = System.nanoTime();
  	   int points = 0;
  	   int lives = 3;
  	   String guess = "";
  	   String ans =  "";
  	   while((t - System.nanoTime())*-1 < 6e10 && lives > 0) {
  	   		guess = nouns[(int)(Math.random()*100)];
       		System.out.println(guess);
       		ans = scan.nextLine();
       		if(ans.equals(guess)) {
       			points += 1;
       			System.out.println(coms[(int)(Math.random()*11)]);
       		}
       		else {
       			lives--;
       		}
       		System.out.println("You have " + points + " points.");
       		System.out.println("You have " + lives + " lives.");
       		System.out.println("You have " + (int)((6e10 - ((t - System.nanoTime())*-1))/1e9) + " seconds left");
  	   }
  	   System.out.println("Looks like you ran out of time or lives!");
  	   System.out.println("Your words per minute is " + points + ". Good Job!");
    }
}
