public class Card
{

    //Instance variables for suit, rank, and value for the cards
    private String suit;
    private int value;
    private String rank;

    //Constructor for the cards
    public Card(String rank, String suit, int value)
    {
        this.suit = suit;
        this.value = value;
        this.rank = rank;

    }

    //Sheet says I need the getters and setters for each of these
    public String getSuit()
    {
        return suit;
    }

    public void setSuit(String newSuit)
    {
        suit = newSuit;
    }

    //Gets the value, which can be used to find hand value
    public int getValue()
    {
        return value;
    }

    public void setValue(int newValue)
    {
        value = newValue;
    }

    public String getRank()
    {
        return rank;
    }

    public void setRank(String newRank)
    {
        rank = newRank;
    }

    //A toString which returns the card as rank + of + suit
    public String toString()
    {
        return rank + " of " + suit;
    }
}

