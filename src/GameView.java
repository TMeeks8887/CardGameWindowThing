// Teddy Meeks
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends JFrame
{
    // Title
    public static final String TITLE = "BlackJack";
    // Final int's to use when drawing the board
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
    // Counts number of players so that I can display player X when enter player name
    private int playerCounter = 0;

    // Constructs the window
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
    // Resets the screen to white, called during every paint call
    public void reset(Graphics g)
    {
        // Resets screen to white
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
    // Draws the welcome screen
    public void drawWelcome(Graphics g)
    {
        // Sets color to black because reset had color as white
        g.setColor(Color.BLACK);

        g.drawString("Hello! ", X_OFFSET + 20, Y_OFFSET + 20);
        g.drawString("Thank you for playing BlackJack! ", X_OFFSET + 20, Y_OFFSET + 40);
    }

    // Draws the rules
    public void drawRules(Graphics g)
    {
        // Sets color to black because reset had color as white
        g.setColor(Color.BLACK);

        g.drawString("Rules: ", X_OFFSET + 20, Y_OFFSET + 20);
        g.drawString("Try to get as close to 21 without hitting it as you can", X_OFFSET + 20, Y_OFFSET + 40);
        g.drawString("Each card's value is it's number, face cards are 10, and Ace is 11", X_OFFSET + 20, Y_OFFSET + 60);
        g.drawString("Person playing as dealer should only hit if they have 16 or below", X_OFFSET + 20, Y_OFFSET + 80);
        g.drawString("The person closest to 21 wins!", X_OFFSET + 20, Y_OFFSET + 100);
        g.drawString("If there are multiple people that are the closest, there are multiple winners", X_OFFSET + 20, Y_OFFSET + 120);
        g.drawString("If everyone goes over 21, there are no winners", X_OFFSET + 20, Y_OFFSET + 140);
        g.drawString("The dealer's hand is showing at all times, you play as the dealer as well.", X_OFFSET + 20, Y_OFFSET + 160);
    }

    // Draws asking the user how many players
    public void drawGetNumPlayers(Graphics g)
    {
        // Sets color to black because reset had color as white
        g.setColor(Color.BLACK);

        g.drawString("How many players? (1-7)", X_OFFSET + 20, Y_OFFSET + 20);
    }
    // Draws prompting the user to enter the name of each player
    // Count is brought in to be able to prompt for each number player
    public void drawGetNames(Graphics g, int count)
    {
        g.setColor(Color.BLACK);

        // Enter name + count so that the screen doesn't stay stagnant
        g.drawString("Enter Name " + count + ": "  , X_OFFSET + 20, Y_OFFSET + 20);
    }
    // Draws each players name on the left side of the screen
    public void drawNames(Graphics g)
    {
        g.setColor(Color.BLACK);
        int numPlayers = ref.getNumPlayers();
        // The i both serves as a way to change the player name and the y position
        for (int i = 0; i < numPlayers; i++)
        {
            g.drawString("Player " + ref.getPlayerName(i), X_OFFSET, Y_OFFSET  + i * (HEIGHT - Y_OFFSET) / numPlayers);
        }
    }
    // Draws the dealer in the top right
    public void drawDealer(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Dealer ", DEALER_X_VAL, Y_OFFSET);
    }
    // Draws each players cards under each player
    // Seperate functions to make it easier to deal with the code
    public void drawCards(Graphics g) {
        // Get the number of players to iterate over each player
        int numPlayers = ref.getNumPlayers();
        // Makes a copy of all the players to get the cards from
        ArrayList<Player> players = ref.getPlayers();
        // Iterate over each player
        for (int i = 0; i < numPlayers + 1; i++)
        {
            // Copies all the card images of the player
            ArrayList<Image> cardImages = ref.getCardImages(i);
            // Draw each card image for the current player
            for (int j = 0; j < players.get(i).getCards().size() ; j++)
            {
                // Gets each card image by iterating through j with regard to hand size
                Image originalImage = cardImages.get(j);

                // Adjust the X and Y offsets to position the cards properly on the screen
                int cardX = X_OFFSET + j * 40;
                int cardY = Y_OFFSET + 5 + i * (HEIGHT - Y_OFFSET) / numPlayers;
                // Draw the resized card image
                g.drawImage(originalImage, cardX, cardY, NEW_WIDTH, NEW_HEIGHT, this);
            }
        }
        // For the Dealer since it's in a different location
        ArrayList<Image> cardImages = ref.getCardImages(numPlayers);
        // Iterate to draw each of the dealers cards
        for (int j = 0; j < players.get(numPlayers).getCards().size(); j++)
        {
            Image originalImage = cardImages.get(j);
            int cardX = DEALER_X_VAL + j * 40;
            int cardY = Y_OFFSET + 5;
            // Draw the resized card image
            g.drawImage(originalImage, cardX, cardY, NEW_WIDTH, NEW_HEIGHT, this);
        }
    }
    // Draws in the bottom right which player's turn it is and how to hit
    public void drawPlayerTurn (Graphics g, int count)
    {
        g.setColor(Color.BLACK);
        g.drawString("Does " + ref.getPlayerName(count) + " want to hit? (put in yes/no or y/n)", DEALER_X_VAL, ASK_IF_HIT);
    }
    // Draws all the names, cards, and whose turn it is to hit
    public void drawNamesDealerCard(Graphics g)
    {
        drawNames(g);
        drawDealer(g);
        drawCards(g);
        drawPlayerTurn(g, ref.getNameCounter());
    }
    // Draws a screen prompting the user if they want to play again
    public void drawPlayAgain(Graphics g)
    {
        g.setColor(Color.BLACK);

        String winner = ref.getWinner(ref.getPlayers());
        g.drawString(winner, X_OFFSET + 20, Y_OFFSET + 20);
        g.drawString("Do you still want to play? (yes/y)", X_OFFSET + 20, Y_OFFSET + 40);
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
        // Prints to ask for number of players
        if (ref.getGameState() == 2)
        {
            drawGetNumPlayers(g);
        }
        // Prints to get each name
        if (ref.getGameState() == 3)
        {
            // Player counter goes up so that it can be passed in to change what the diplay says without a for loop
            playerCounter++;
            drawGetNames(g, playerCounter);
        }
        // Draws the player names, cards, and the prompt of whose turn it is
        if (ref.getGameState() == 4)
        {
            drawNamesDealerCard(g);
        }
        // Draws the winner and prompts asking if you want to play again
        if (ref.getGameState() == 5)
        {
            // Sets the nameCounter to -1 because in askIfHit it always starts with counter++ because if the person hits
            // It does counter-- so that the name doesn't change if the player gets the choice to hit again
            ref.setNameCounter(-1);
            drawPlayAgain(g);
        }
    }
}
