import java.util.ArrayList;

public class Deck
{

    //Instance variables of deck and cards left
    private ArrayList<Card> cards;
    private static int cardsLeft;


    //Constructor to create a deck
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

    //Checks if the deck is empty, useful to know when to reset
    public boolean isEmpty()
    {
        if(cardsLeft == 0)
        {
            return true;
        }
        return false;
    }

    //Returns the amount of cards in the deck, don't need it because I just check if empty to know when to reset
    //But sheet says it's necessary
    public int getCardsLeft()
    {
        return cardsLeft;
    }

    //Returns the last card from the deck,
    //Makes it so cards left decreases by 1 to check when every card has been played
    public Card deal()
    {
        if (isEmpty())
        {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    //Shuffles the deck in the manner given in the sheet
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
}
