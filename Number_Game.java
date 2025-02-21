import java.util.Random;
import java.util.Scanner;

public class Number_Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random ran= new Random();
        
        int minRange = 1, maxRange = 100;
        int maxAttempts = 5;
        int score = 0;
        boolean playAgain = true;
        
        System.out.println("Number Guessing Game!");
        
        while (playAgain) {
            // min_number = 1 and max_number = 100
            int targetNumber = ran.nextInt(100)+1;
            int attemptsLeft = maxAttempts;
            boolean guessedCorrectly = false;
            
            System.out.println("I have selected a number between " + minRange + " and " + maxRange + ". Can you guess it?");
            
            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                
                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    score += 10;
                    break;
                } else if (userGuess > targetNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
                
                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }
            
            if (!guessedCorrectly) {
                System.out.println("Out of attempts! The correct number was " + targetNumber);
            }
            
            System.out.println("Your current score: " + score);
            
            System.out.print("Do you want to play again? (yes/no): ");
            String response = sc.next().toLowerCase();
            playAgain = response.equals("yes");
        }
        
        System.out.println("Thank you for playing! Your final score is: " + score);
        sc.close();
    
}
}


