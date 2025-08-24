package conditional.statements;
import java.util.Scanner;
import java.util.Random;



public class NumberGuesser {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		
		String playAgain = "yes";
		
		while (playAgain.equalsIgnoreCase("yes")) {
			
			int numberToGuess = rand.nextInt(100) + 1; // 1 to 100
			int attempt = 0;
			int guess = 0;
			int maxAttempts = 5;
			
			System.out.println("A number has been generated between 1-100.");
			
			while (guess != numberToGuess && attempt < maxAttempts) {
				
				System.out.print("Guess a number: ");
				guess = sc.nextInt();
				attempt++;
				
				if (guess < numberToGuess) {
					System.out.println("Sorry too low");
				}
				else if (guess > numberToGuess) {
					System.out.println("Sorry too high");
				}
				else {
					System.out.println("You won in attempt: " + attempt);
				}
			}
			
			if (guess != numberToGuess) {
				System.out.println("You lost! The number was: " + numberToGuess);
			}
			
			System.out.print("Do you want to play the game again (yes/no)? ");
			playAgain = sc.next();
		}
		
		
		
	}

}
