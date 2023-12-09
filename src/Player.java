import java.util.ArrayList;

public class Player {

    //ArrayList of cards which gets used for each player to represent their hand
    private ArrayList<Card> hand;
    //Points for each player, if win a round, gain one point
    private int points;
    //Names of each player
    private String name;

    //Player constructor which takes in an array of cards as well to give to the player
    //I shouldn't have it, but the sheet says it's required
    public Player(String name, ArrayList<Card> cards)
    {
        this.name = name;
        this.points = 0;
        this.hand = cards;
    }

    //Player constructor, creates the player, and gives each one an empty hand, name, and 0 points to start
    public Player(String name)
    {
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<Card>();
    }

    //Gets the cards that a player has
    public ArrayList<Card> getCards()
    {
        return hand;
    }

    //Gets the points that a player has, says it's needed on the sheet, but I use the toString to print the points
    public int getPoints()
    {
        return points;
    }

    //Gets the name of the player
    public String getName()
    {
        return name;
    }

    //Adds a point to the player that wins
    public void addPoints(int addPoints)
    {
        this.points = points + addPoints;
    }

    //Adds a card to the players hand
    public void addCard(Card newCard)
    {
        hand.add(newCard);
    }

    //Removes all cards from the players hand, used to reset hands
    public void removeCards(Player name)
    {
        while (hand.size() > 0)
        {
            hand.remove(0);
        }
    }

    //Prints out the data of the player
    public String toString()
    {
        return "\n" + name + " has " + points + " points " + " \n" + name + "'s cards: " + hand + "\n";
    }
}
