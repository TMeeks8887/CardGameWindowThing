import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand = new ArrayList<>();
    private int points = 0;
    private String name;

    public Player(String name, ArrayList<Card> cards, int points)
    {
        this.name = name;
        points = 0;
        this.hand = new ArrayList<>();
    }

    public ArrayList<Card> getCards()
    {
        return hand;
    }

    public int getPoints()
    {
        return points;
    }

    public String getName()
    {
        return name;
    }

    public void addPoints(int addPoints)
    {
        points = points + addPoints;
    }

//    to-do
//    public void addCard(Card newCard)
//    {
//
//    }

    public String toString()
    {
        return name + " has " + points + " points " + " /n " + name + "'s cards: " + hand;
    }
}
