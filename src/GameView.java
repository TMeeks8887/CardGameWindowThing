import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame
{
    public static final String TITLE = "BlackJack";
    public static final int
            WIDTH = 600,
            HEIGHT = 600,
            Y_OFFSET = 22,
            X_OFFSET = 75,
            LINE_BREAK = 75;

    private Game ref;
    public GameView(Game ref)
    {
        this.ref = ref;

        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
    }
    public void reset(Graphics g)
    {
        // Resets screen to white
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
    public void drawWelcome(Graphics g)
    {
        g.setColor(Color.BLACK);

        g.drawString("Hello! ", X_OFFSET + 20, Y_OFFSET - 20);
        g.drawString("Thank you for playing BlackJack! ", X_OFFSET + 40, Y_OFFSET - 20);
    }

    public void drawRules(Graphics g)
    {
        g.setColor(Color.BLACK);

        g.drawString("Rules: ", X_OFFSET + 20, Y_OFFSET - 20);
        g.drawString("Try to get as close to 21 without hitting it as you can", X_OFFSET + 20 + LINE_BREAK, Y_OFFSET - 20);
        g.drawString("Each card's value is it's number, face cards are 10, and Ace is 11", X_OFFSET + 20 + LINE_BREAK * 2, Y_OFFSET - 20);
        g.drawString("Person playing as dealer should only hit if they have 16 or below", X_OFFSET + 20 + LINE_BREAK * 3, Y_OFFSET - 20);
        g.drawString("The person closest to 21 wins!", X_OFFSET + 20 + LINE_BREAK * 4, Y_OFFSET - 20);
        g.drawString("If there are multiple people that are the closest, there are multiple winners", X_OFFSET + 20 + LINE_BREAK * 5, Y_OFFSET - 20);
        g.drawString("Each time you win a round you gain 1 point", X_OFFSET + 20 + LINE_BREAK * 6, Y_OFFSET - 20);
        g.drawString("The person with the most points once you decide to end wins the whole thing.", X_OFFSET + 20 + LINE_BREAK * 7, Y_OFFSET - 20);
    }
    public void drawNames(Graphics g)
    {
        g.setColor(Color.BLACK);

        for (int i = 0; i < numPlayers; i++)
        {
            g.drawString("(i Name)", X_OFFSET + 20 + i * WIDTH/numPlayers, Y_OFFSET - 20);
        }
    }
    public void paint(Graphics g)
    {
        // Resets screen to white
        reset(g);

        // Prints the welcome statement
        if (ref.getGameState() == 0)
        {
            drawWelcome(g);
        }


        // Prints the rules
        if (ref.getGameState() == 1)
        {
            drawRules(g);
        }


        // Draws the player names
        if (ref.getGameState() == 2)
        {
            drawNames(g);
        }

        // Draw the cards when state is 3
        else
        {

        }
    }
}
