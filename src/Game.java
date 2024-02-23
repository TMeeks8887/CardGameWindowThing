// Teddy Meeks
// December 9th

// Importing scanner and arrayList
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.*;
public class Game {

    // Creating a scanner to get input from user
    Scanner s = new Scanner(System.in);
    // Create an arrayList of players to add the players into
    private ArrayList<Player> players;
    // Defining the amount of players, so it can keep prompting until an appropriate amount is put in
    private int numPlayers = 0;
    // Create a deck instance variable
    private Deck wholeDeck;
    // Define it up here to be used in askIfHit
    private String hit;
    // Define playing as "yes" so then it goes, and after each round they can choose to play
    private String playing = "yes";
    // Game state for different screen
    private int gameState = 0;
    private int nameCounter = -1;
    private GameView window;

    // Game constructor which creates the deck, and initializes players
    public Game()
    {
        players = new ArrayList<Player>();
        numPlayers = 0;
        String Ranks[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int Values[] = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        String Suits[] = {"Spades", "Hearts", "Diamonds", "Clubs"};
        wholeDeck = new Deck(Ranks, Suits, Values);

        this.window = new GameView(this);
    }

    public int getNameCounter() {
        return nameCounter;
    }

    public void setNameCounter(int nameCounter) {
        this.nameCounter = nameCounter;
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }
    public ArrayList<Image> getCardImages(int index)
    {
        ArrayList<Image> playerCardImages = new ArrayList<>();
        for (int i = 0; i < players.get(index).getCards().size(); i++)
        {
            playerCardImages.add(players.get(index).getCards().get(i).getImageJ());
        }
        return playerCardImages;
    }

    public String getPlayerName(int index)
    {
        return players.get(index).getName();
    }

    // Gets the amount of players and goes through adding each to the arraylist
    public void activePlayersHowMany()
    {
        // Traditional blackjack has less than 8 players
        while (numPlayers < 1 || numPlayers > 7) {
            System.out.println("How many players?");
            numPlayers = s.nextInt();
        }
    }
    public void activePlayersEnterName()
    {
        String name;
        for (int i = 0; i < numPlayers; i++)
        {
            System.out.println("Enter name: ");
            window.repaint();
            name = s.nextLine(); // Read the player's name
            // Want to add the players to an arraylist to be called on later
            players.add(new Player(name));
        }
    }

    // Creates a dealer and adds to arrayList
    public void makeDealer()
    {
        players.add(new Player("dealer"));
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    // Prints the instructions
    public void printInstructions()
    {
        System.out.println("Hello! ");
        System.out.println("Thank you for playing BlackJack!");
        System.out.println("Rules: ");
        System.out.println("Try to get as close to 21 without hitting it as you can");
        System.out.println("Each card's value is it's number, face cards are 10, and Ace is 11");
        System.out.println("Person playing as dealer should only hit if they have 16 or below");
        System.out.println("The person closest to 21 wins!");
        System.out.println("If there are multiple people that are the closest, there are multiple winners");
        System.out.println("Each time you win a round you gain 1 point");
        System.out.println("The person with the most points once you decide to end wins the whole thing.");
    }

    // Asks if the player wants to hit, if they do it first checks if there's a card, then adds the card to their hand
    // Repeats until they either bust or don't want to hit anymore
    public void askIfHit(Player name)
    {
        nameCounter++;
        window.repaint();
        System.out.println("Does " + name.getName() + " want to hit? (put in yes/no or y/n)");
        hit = s.nextLine();
        if (hit.equals("yes") || hit.equals("y"))
        {
            if (wholeDeck.isEmpty())
            {
                wholeDeck =  resetDeck(wholeDeck);
            }
            name.addCard(wholeDeck.deal());
            if (checkIf21(name) == true)
            {
                window.repaint();
                return;
            }
            window.repaint();
            nameCounter--;
            askIfHit(name);
        }
    }

    // Gives each player 2 cards to start with
    // If the deck is empty then it resets the deck
    public void startingHand(Player name)
    {
        for (int j = 0; j < 2; j++)
        {
            if (wholeDeck.isEmpty())
            {
                wholeDeck = resetDeck(wholeDeck);
            }
            name.addCard(wholeDeck.deal());
        }
    }

    // Gets the value of the players hand and returns it to be used in checkIf21 and getWinner
    public int getHandValue(Player name)
    {
        int value = 0;
        for(int i = 0; i < name.getCards().size(); i++)
        {
            // Value adds however much the specific card has
            value += name.getCards().get(i).getValue();
        }
        return value;
    }


    // Checks if the players hand has a value greater than 21, if they do then it prints they busted and returns true
    public boolean checkIf21(Player name)
    {
        int value = getHandValue(name);
        if (value > 21)
        {
            System.out.println("You busted!");
            name.setName(name.getName() + " (Busted)");
            return true;
        }
        return false;
    }

    // Gets the winner(s)
    public String getWinner(ArrayList<Player> players)
    {
        String winners;
        int winnerPoints = 0;
        ArrayList<String> winnerName = new ArrayList<String>();
        for (int i = 0; i < numPlayers + 1; i++)
        {
            // Compares all the players together to check how many points is needed to win
            if (getHandValue(players.get(i)) > winnerPoints && getHandValue(players.get(i)) <= 21)
            {
                winnerPoints = getHandValue(players.get(i));
            }
        }
        for (int i = 0; i < numPlayers + 1; i++)
        {
            // Goes through all the players seeing if they have the amount of points to win
            if (getHandValue(players.get(i)) == winnerPoints)
            {
                // If they do, they get added to the arraylist of winners
                winnerName.add(players.get(i).getName());
                players.get(i).addPoints(1);
            }
        }
        // Prints the winner(s)
        System.out.println("The winner is " + winnerName);
        if (winnerName.isEmpty())
        {
            return "There is no winner";
        }
        winners = ("The winner is " + winnerName);
        return winners;
    }

    // Resets a players hand, gets called in a for-loop to reset all
    public void resetHands(Player name)
    {
        name.removeCards(name);
    }

    // Resets the deck to a full deck
    public Deck resetDeck(Deck wholeDeck)
    {
        String Ranks[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int Values[] = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        String Suits[] = {"Spades", "Hearts", "Diamonds", "Clubs"};
        return new Deck(Ranks, Suits, Values);
    }
    public void resetNames(Player name)
    {
        int lengthBusted = name.getName().length();
        int originalLength = lengthBusted - 9;
        String originalName = name.getName().substring(0, originalLength);
        name.setName(originalName);
    }

    public void nextGameState()
    {
        System.out.println("Done with this screen? (yes/y)");
        String answer = s.nextLine();
        if (answer.equals("yes") || answer.equals("y"))
        {
            gameState++;
        }
    }
    public void goThroughGameState()
    {
        window.repaint();
        nextGameState();
    }
    // Plays the game
    public void playGame()
    {
        // Game States 0 and 1
        for (int i = 0; i < 2; i++)
        {
            goThroughGameState();
        }

        // Game state 2
        window.repaint();
        activePlayersHowMany();
        String blank = s.nextLine();
        gameState++;

        // Game state 3

//        window.repaint();
        activePlayersEnterName();


        makeDealer();

        gameState++;
        // Game state 4
        window.repaint();


        // Checking if they still want to continue
        while (playing.equals("yes") || playing.equals("Yes") || playing.equals("y") || playing.equals("Y"))
        {
            // Shuffles the deck
            wholeDeck.shuffle();
            // Goes through each player to add cards to
            for (int i = 0; i < numPlayers + 1; i++)
            {
                startingHand(players.get(i));
                // Can get 2 aces, so have to check if they start with 22
                checkIf21(players.get(i));
            }
            window.repaint();

            // Goes through each player to check if they want to hit
            for (int i = 0; i < numPlayers + 1; i++)
            {
                askIfHit(players.get(i));
            }
            // Gets winner(s)
            getWinner(players);
            // Checks if they want to keep playing, if they type something other than yes/Yes/y/Y then it ends the game
            // Game state 5
            gameState++;
            window.repaint();


            playing = s.nextLine();
            // Goes through each player resetting their hand
            for (int i = 0; i < numPlayers + 1; i++)
            {
                if (getHandValue(players.get(i)) > 21 )
                {
                    resetNames(players.get(i));
                }
                resetHands(players.get(i));
            }
            gameState--;
        }
    }

    public int getGameState()
    {
        return gameState;
    }

    // Main
    public static void main(String[] args)
    {
        Game game = new Game();
        game.playGame();
    }
}
