import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RockPaperScissorsGameTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayInputStream inputStream = new ByteArrayInputStream("rock\nq\n".getBytes());

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        System.setIn(inputStream);
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void testPlayRock() {
        RockPaperScissorsGame instance = new RockPaperScissorsGame();
        instance.play();
        String consoleOutput = outputStream.toString();

        assertThat(consoleOutput, containsString("Welcome to Rock, Paper, Scissors!"));
        assertThat(consoleOutput, containsString("Enter your choice: rock, paper, or scissors. To quit, type 'q'."));
        assertThat(consoleOutput, containsString("Your choice: "));
        assertThat(consoleOutput, containsString("Computer's choice: "));
        assertThat(consoleOutput, containsString("Result: "));
        assertThat(consoleOutput, containsString("Thank you for playing. Goodbye!"));
    }

    @Test
    public void testPlayQuit() {
        ByteArrayInputStream quitInputStream = new ByteArrayInputStream("q\n".getBytes());
        System.setIn(quitInputStream);

        RockPaperScissorsGame instance = new RockPaperScissorsGame();
        instance.play();
        String consoleOutput = outputStream.toString();

        assertThat(consoleOutput, containsString("Welcome to Rock, Paper, Scissors!"));
        assertThat(consoleOutput, containsString("Enter your choice: rock, paper, or scissors. To quit, type 'q'."));
        assertThat(consoleOutput, containsString("Thank you for playing. Goodbye!"));
    }

    @Test
    public void testPlayInvalidInput() {
        ByteArrayInputStream invalidInputStream = new ByteArrayInputStream("invalid\nq\n".getBytes());
        System.setIn(invalidInputStream);

        RockPaperScissorsGame instance = new RockPaperScissorsGame();
        instance.play();
        String consoleOutput = outputStream.toString();

        assertThat(consoleOutput, containsString("Welcome to Rock, Paper, Scissors!"));
        assertThat(consoleOutput, containsString("Enter your choice: rock, paper, or scissors. To quit, type 'q'."));
        assertThat(consoleOutput, containsString("Invalid choice. Please choose rock, paper, or scissors."));
        assertThat(consoleOutput, containsString("Thank you for playing. Goodbye!"));
    }

    // Add more test cases for different game outcomes (win, lose, tie)
    @Test
    public void testPlayRockWin() {
        ByteArrayInputStream rockWinInputStream = new ByteArrayInputStream("rock\nq\n".getBytes());
        System.setIn(rockWinInputStream);

        RockPaperScissorsGame instance = new RockPaperScissorsGame();
        instance.play();
        String consoleOutput = outputStream.toString();

        assertThat(consoleOutput, containsString("Result: You win!"));
    }

    @Test
    public void testPlayRockLose() {
        ByteArrayInputStream rockLoseInputStream = new ByteArrayInputStream("paper\nq\n".getBytes());
        System.setIn(rockLoseInputStream);

        RockPaperScissorsGame instance = new RockPaperScissorsGame();
        instance.play();
        String consoleOutput = outputStream.toString();

        assertThat(consoleOutput, containsString("Result: Computer wins!"));
    }

    @Test
    public void testPlayRockTie() {
        ByteArrayInputStream rockTieInputStream = new ByteArrayInputStream("rock\nrock\nq\n".getBytes());
        System.setIn(rockTieInputStream);

        RockPaperScissorsGame instance = new RockPaperScissorsGame();
        instance.play();
        String consoleOutput = outputStream.toString();

        assertThat(consoleOutput, containsString("Result: It's a tie!"));
    }
}
