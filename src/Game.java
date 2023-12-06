//Teddy Meeks
//December 7th

import java.util.Scanner;
import java.util.ArrayList;
public class Game {

    Scanner s = new Scanner(System.in);

    private Deck deck;
    private ArrayList<Player> players;

    public Game()
    {
        printInstructions();
        deck = new Deck();
        players = new ArrayList<Player>();
        String Ranks[] = {"2", "3", "4"};
        int Values[] = {2, 3, 4};
        String Suits[] = {"Spades", "Hearts", "Diamonds", "Clubs"};
        Deck wholeDeck = new Deck(Ranks, Suits, Values);
        System.out.println("How many players?");
        int numPlayers = s.nextInt();
        System.out.print("");
        String name = s.nextLine();

        for (int i = 0; i < numPlayers; i++)
        {
            System.out.println("Enter name: ");
            name = s.nextLine();
            //want to add the players to an arraylist to be called on later
            players.add(new Player(name));
        }
        System.out.println("The players, their points, and their cards are:");
        System.out.println(players);
    }

    public void printInstructions()
    {
        System.out.println("Hello! ");
        System.out.println("Thank you for playing BlackJack!");
        System.out.println("Rules: ");
        System.out.println("Try to get as close to 21 without hitting it as you can");
        System.out.println("The person closest to 21 wins!");
    }

    public static void main(String[] args)
    {
        Game game = new Game();
    }

//    public void startingHand(Player name)
//    {
//        for (int i = 0; i < 2; i++)
//        {
//            name.addCard(Deck.deal());
//        }
//    }
}
