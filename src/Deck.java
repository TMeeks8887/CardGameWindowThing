import java.util.ArrayList;

public class Deck
{

    private ArrayList<Card> cards;
    private static int cardsLeft;


    public Deck (String[] rank, String[] suit, int[] value)
    {
        cards = new ArrayList<Card>();
        for (int i = 0; i < rank.length; i++)
        {
            for (String eachSuit : suit)
            {
                cards.add(new Card(rank[i], eachSuit, value[i]));
            }
        }
        cardsLeft = cards.size();
    }

    public Deck() { }

    public ArrayList<Card> getArrList()
    {
        return cards;
    }

    public boolean isEmpty()
    {
        if(cardsLeft == 0)
        {
            return true;
        }
        return false;
    }

    public int getCardsLeft()
    {
        return cardsLeft;
    }

    public Card deal()
    {
        if (isEmpty())
        {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle()
    {
        Card placeHolder;
        for (int i = cards.size() - 1; i > 0; i--)
        {
            int randNum = (int) ((Math.random() * i) + 1);
            placeHolder = cards.get(i);
            cards.set(i, cards.get(randNum));
            cards.set(randNum, placeHolder);
        }
    }

    public ArrayList<Card> getCards()
    {
        return this.cards;
    }

}
