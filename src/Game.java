//Teddy Meeks
//December 7th

import java.util.Scanner;
import java.util.ArrayList;
public class Game {

    Scanner s = new Scanner(System.in);
    private ArrayList<Player> players;
    private int numPlayers;
    private Deck wholeDeck;
    private String hit;
    private boolean playing = true;

    public Game()
    {
        players = new ArrayList<Player>();
        numPlayers = 0;
        String Ranks[] = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int Values[] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        String Suits[] = {"Spades", "Hearts", "Diamonds", "Clubs"};
        wholeDeck = new Deck(Ranks, Suits, Values);
    }

    public void activePlayers()
    {
        System.out.println("How many players?");
        numPlayers = s.nextInt();
        System.out.print("");
        String name = s.nextLine();

        for (int i = 0; i < numPlayers; i++)
        {
            System.out.println("Enter name: ");
            name = s.nextLine();
            //want to add the players to an arraylist to be called on later
            players.add(new Player(name));
        }
    }

    public void makeDealer()
    {
        players.add(new Player("dealer"));
    }

    public void printInstructions()
    {
        System.out.println("Hello! ");
        System.out.println("Thank you for playing BlackJack!");
        System.out.println("Rules: ");
        System.out.println("Try to get as close to 21 without hitting it as you can");
        System.out.println("The person closest to 21 wins!");
    }

    public void askIfHit(Player name)
    {
        System.out.println("Does " + name.getName() + " want to hit? (put in yes/no or y/n)");
        hit = s.nextLine();
        if (hit.equals("yes") || hit.equals("y"))
        {
            name.addCard(wholeDeck.deal());
            System.out.println(name.getCards());
            if (checkIf21(name) == true) {
                return;
            }
            askIfHit(name);
        }
    }

    public void startingHand(Player name)
    {
        for (int j = 0; j < 2; j++)
        {
            name.addCard(wholeDeck.deal());
        }
    }

    public void printPlayers()
    {
        System.out.println("The players, their points, and their cards are:");
        System.out.println(players);
    }

    public int getHandValue(Player name)
    {
        int value = 0;
        for(int i = 0; i < name.getCards().size(); i++)
        {
            value += name.getCards().get(i).getValue();
        }
        return value;
    }

    public boolean checkIf21(Player name)
    {
        int value = getHandValue(name);
        if (value > 21)
        {
            System.out.println("You busted!");
            return true;
        }
        return false;
    }

    public void getWinner(ArrayList<Player> players)
    {
        int winnerPoints = 0;
        ArrayList<String> winnerName = new ArrayList<String>();
        for (int i = 0; i < numPlayers + 1; i++)
        {
            if (getHandValue(players.get(i)) > winnerPoints && getHandValue(players.get(i)) <= 21)
            {
                winnerPoints = getHandValue(players.get(i));
            }
        }
        for (int i = 0; i < numPlayers + 1; i++)
        {
            if (getHandValue(players.get(i)) == winnerPoints)
            {
                winnerName.add(players.get(i).getName());
                players.get(i).addPoints(1);
            }
        }

        System.out.println("The winner is " + winnerName);
    }

    public void resetHands(Player name)
    {
        name.removeCards(name);
    }

    public  void playGame()
    {
        printInstructions();
        makeDealer();
        activePlayers();
        wholeDeck.shuffle();
        while (playing = true)
        {
            // goes through each player to add cards to
            for (int i = 0; i < numPlayers + 1; i++)
            {
                startingHand(players.get(i));
            }
            printPlayers();
            for (int i = 0; i < numPlayers + 1; i++)
            {
                askIfHit(players.get(i));
            }
            getWinner(players);

            System.out.println("Do you still want to play? (True =  yes/False = no)");
            playing = Boolean.parseBoolean(s.nextLine());
            for (int i = 0; i < numPlayers; i++)
            {
                resetHands(players.get(i));
            }
        }
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.playGame();
    }
}
