import java.util.Scanner;
import static java.lang.System.*;

public class Practice {

	Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Practice p = new Practice();
        p.menu();
    }

    public void menu() {
    	String line = "";
        out.println("What would you like to practice on?");
        out.println("[Modulo]");
        out.println("[Converting] Between Bases");
        out.println("[Binary] Addition and Subtraction");
        out.println("[EXIT]");
        line = scan.nextLine();
        while(true) {
        	if(line.equals("Modulo")) {
        		modP();
       			break;
        	}
        	if(line.equals("Converting")) {
        		conP();
        		break;
        	}
        	if(line.equals("Binary")) {
        		binP();
        		break;
        	}
        	if(line.equals("EXIT")) {
        		exit(0);
        	}
        	out.println("Incorrect Input. Try Again.");
        	line = scan.nextLine();
        }
    }

    public void modP() {
    	int a = (int)(Math.random()*100);
    	int b = (int)(Math.random()*100);
    	String line = "";
    	out.printf("What is %d %% %d?\n", a, b);
    	int guess = scan.nextInt();
    	if(guess == a % b) {
    		out.println("Yay! You got it right!");
    	}
    	else {
    		out.println("Uh-oh. You didn't get that right. The correct answer is " + (a % b));
    	}
    	out.println("Wanna go again?");
    	out.println("[YES] or [NO]");
    	String ans = scan.nextLine();
        ans.toUpperCase();
    	while(true) {
    		if(ans.equals("YES")) {
    			modP();
    			break;
    		}
    		if(ans.equals("NO")) {
    			menu();
    			break;
    		}
    		out.println("Incorrect Input. Try Again.");
        	ans = scan.nextLine();
    	}
    }

    public void conP() {
    	int ans = (int)(Math.random() * 257);
    	int base1 = (int)((Math.random() * 17) + 1);
    	int base2 = (int)((Math.random() * 17) + 1);
		out.println("What is the base " + base1 + " number " + Integer.toString(ans, base1) + " in base " + base2 + "?");
		String guess = scan.nextLine();
		if(ans == Integer.parseInt(guess, base2)) {
			out.println("Yay! You got it right!");
		}
		else {
			out.println("Uh-oh. You didn't get that right. The correct answer is " + ans);
		}
		out.println("Wanna go again?");
    	out.println("[YES] or [NO]");
    	String a = scan.nextLine();
        a.toUpperCase();
    	while(true) {
    		if(a.equals("YES")) {
    			conP();
    			break;
    		}
    		if(a.equals("NO")) {
    			menu();
    			break;
    		}
    		out.println("Incorrect Input. Try Again.");
        	a = scan.nextLine();
    	}
    }

    public void binP() {
    	byte r = (byte)(Math.random()*128);
        byte op = (byte)(Math.random()*128);
        out.println("In a signed byte...");
        out.println(Integer.toBinaryString(r) + " plus " + Integer.toBinaryString(op) + " is: ");
        String ans = scan.nextLine();
        if(ans.equals(Integer.toBinaryString(r))) {
            out.println("Yay! You got it right!");
        }
        else {
    		out.println("Uh-oh. You didn't get that right. The correct answer is " + Integer.toBinaryString(r + op));
    	}
        out.println("Wanna go again?");
    	out.println("[YES] or [NO]");
    	String a = scan.nextLine();
        a.toUpperCase();
    	while(true) {
    		if(a.equals("YES")) {
    			binP();
    			break;
    		}
    		if(a.equals("NO")) {
    			menu();
    			break;
    		}
    		out.println("Incorrect Input. Try Again.");
        	a = scan.nextLine();
    	}
    }
}
