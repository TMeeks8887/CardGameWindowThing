import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;
    private int points;
    private String name;

    public Player(String name, ArrayList<Card> cards)
    {
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<>();
    }

    public Player(String name)
    {
        this.name = name;
        this.points = 0;
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


    public void addCard(Card newCard)
    {
        hand.add(newCard);
    }

    public String toString()
    {
        return name + " has " + points + " points " + " \n" + name + "'s cards: " + hand;
    }
}
