import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends JFrame
{
    public static final String TITLE = "BlackJack";
    public static final int
            WIDTH = 600,
            HEIGHT = 600,
            Y_OFFSET = 42,
            X_OFFSET = 20,
            DEALER_X_VAL = 320,
            NEW_HEIGHT = 60,
            NEW_WIDTH = 40,
            ASK_IF_HIT = 350;

    private Game ref;
    private int playerCounter = 0;

    public GameView(Game ref)
    {
        // Backend passed in
        this.ref = ref;

        // Constructs the window
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

        g.drawString("Hello! ", X_OFFSET + 20, Y_OFFSET + 20);
        g.drawString("Thank you for playing BlackJack! ", X_OFFSET + 20, Y_OFFSET + 40);
    }

    public void drawRules(Graphics g)
    {
        g.setColor(Color.BLACK);

        g.drawString("Rules: ", X_OFFSET + 20, Y_OFFSET + 20);
        g.drawString("Try to get as close to 21 without hitting it as you can", X_OFFSET + 20, Y_OFFSET + 40);
        g.drawString("Each card's value is it's number, face cards are 10, and Ace is 11", X_OFFSET + 20, Y_OFFSET + 60);
        g.drawString("Person playing as dealer should only hit if they have 16 or below", X_OFFSET + 20, Y_OFFSET + 80);
        g.drawString("The person closest to 21 wins!", X_OFFSET + 20, Y_OFFSET + 100);
        g.drawString("If there are multiple people that are the closest, there are multiple winners", X_OFFSET + 20, Y_OFFSET + 120);
        g.drawString("Each time you win a round you gain 1 point", X_OFFSET + 20, Y_OFFSET + 140);
        g.drawString("The person with the most points once you decide to end wins the whole thing.", X_OFFSET + 20, Y_OFFSET + 160);
    }

    public void drawGetNumPlayers(Graphics g)
    {
        g.setColor(Color.BLACK);

        g.drawString("How many players? (1-7)", X_OFFSET + 20, Y_OFFSET + 20);
    }
    public void drawGetNames(Graphics g, int count)
    {
        g.setColor(Color.BLACK);

        g.drawString("Enter Name " + count + ": "  , X_OFFSET + 20, Y_OFFSET + 20);
    }
    public void drawNames(Graphics g)
    {
        g.setColor(Color.BLACK);
        int numPlayers = ref.getNumPlayers();
        for (int i = 0; i < numPlayers; i++)
        {
            g.drawString("Player " + ref.getPlayerName(i), X_OFFSET, Y_OFFSET  + i * (HEIGHT - Y_OFFSET) / numPlayers);
        }
    }
    public void drawDealer(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Dealer ", DEALER_X_VAL, Y_OFFSET);
    }
    public void drawCards(Graphics g) {
        int numPlayers = ref.getNumPlayers();
        ArrayList<Player> players = ref.getPlayers();
        // Iterate over each player
        for (int i = 0; i < numPlayers + 1; i++)
        {
            ArrayList<Image> cardImages = ref.getCardImages(i);
                // Draw each card image for the current player
            for (int j = 0; j < players.get(i).getCards().size() ; j++)
            {
                Image originalImage = cardImages.get(j);

                // Adjust the X and Y offsets to position the cards properly on the screen
                int cardX = X_OFFSET + j * 40;
                int cardY = Y_OFFSET + 5 + i * (HEIGHT - Y_OFFSET) / numPlayers;
                // Draw the card image
                g.drawImage(originalImage, cardX, cardY, NEW_WIDTH, NEW_HEIGHT, this);
            }
        }
        // For the Dealer since it's in a different location
        ArrayList<Image> cardImages = ref.getCardImages(numPlayers);
        for (int j = 0; j < players.get(numPlayers).getCards().size(); j++)
        {
            Image originalImage = cardImages.get(j);
            int cardX = DEALER_X_VAL + j * 40;
            int cardY = Y_OFFSET + 5;
            g.drawImage(originalImage, cardX, cardY, NEW_WIDTH, NEW_HEIGHT, this);
        }
    }
    public void drawPlayerTurn (Graphics g, int count)
    {
        g.setColor(Color.BLACK);
        g.drawString("Does " + ref.getPlayerName(count) + " want to hit? (put in yes/no or y/n)", DEALER_X_VAL, ASK_IF_HIT);
    }
    public void drawNamesDealerCard(Graphics g)
    {
        drawNames(g);
        drawDealer(g);
        drawCards(g);
        drawPlayerTurn(g, ref.getNameCounter());
    }
    public void drawPlayAgain(Graphics g)
    {
        g.setColor(Color.BLACK);

        String winner = ref.getWinner(ref.getPlayers());
        g.drawString(winner, X_OFFSET + 20, Y_OFFSET + 20);
        g.drawString("Do you still want to play?", X_OFFSET + 20, Y_OFFSET + 40);
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

        if (ref.getGameState() == 2)
        {
            drawGetNumPlayers(g);
        }

        if (ref.getGameState() == 3)
        {
            playerCounter++;
            drawGetNames(g, playerCounter);
        }
        // Draws the player names
        if (ref.getGameState() == 4)
        {
            drawNamesDealerCard(g);
        }
        if (ref.getGameState() == 5)
        {
            ref.setNameCounter(-1);
            drawPlayAgain(g);
        }
    }
}
