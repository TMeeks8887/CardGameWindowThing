public class Card
{

    private String suit;
    private int value;
    private String rank;

    public Card(String suit, int value, String rank )
    {
        this.suit = suit;
        this.value = value;
        this.rank = rank;

    }

    public String getSuit()
    {
        return suit;
    }

    public void setSuit(String newSuit)
    {
        suit = newSuit;
    }

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
}

