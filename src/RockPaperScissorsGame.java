import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsGame {
    private static final String[] CHOICES = {"rock", "paper", "scissors"};

    private String computerChoice;
    private String userChoice;

    public RockPaperScissorsGame() {
        computerChoice = "";
        userChoice = "";
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Rock, Paper, Scissors!");
        System.out.println("Enter your choice: rock, paper, or scissors. To quit, type 'q'.");

        while (true) {
            System.out.print("Your choice: ");
            userChoice = scanner.nextLine().toLowerCase();

            if (userChoice.equals("q")) {
                System.out.println("Thank you for playing. Goodbye!");
                break;
            }

            if (!isValidChoice(userChoice)) {
                System.out.println("Invalid choice. Please choose rock, paper, or scissors.");
                continue;
            }

            computerChoice = getRandomChoice();

            System.out.println("Computer's choice: " + computerChoice);

            String result = determineWinner();
            System.out.println("Result: " + result);
        }

        scanner.close();
    }

    private String getRandomChoice() {
        Random random = new Random();
        return CHOICES[random.nextInt(CHOICES.length)];
    }

    private boolean isValidChoice(String choice) {
        return choice.equals("rock") || choice.equals("paper") || choice.equals("scissors");
    }

    private String determineWinner() {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if (userChoice.equals("rock") && computerChoice.equals("scissors") ||
                userChoice.equals("paper") && computerChoice.equals("rock") ||
                userChoice.equals("scissors") && computerChoice.equals("paper")) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }
}
