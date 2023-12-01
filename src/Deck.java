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
        if (cards.isEmpty())
        {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft + 1);
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
    //basic deck constructor
    public Deck ()
    {
        this.cards = new ArrayList<>();
        String numRank;
        //all the Diamonds
        for (int i = 2; i < 11; i++)
        {
            numRank = Integer.toString(i);
            this.cards.add(new Card(numRank, "Diamond", i));
        }
        this.cards.add(new Card("Jack","Diamond", 10));
        this.cards.add(new Card("Queen", "Diamond", 10));
        this.cards.add(new Card("King","Diamond", 10));
        this.cards.add(new Card("Ace","Diamond", 11));

        //all the Spades
        for (int i = 2; i < 11; i++)
        {
            numRank = Integer.toString(i);
            this.cards.add(new Card(numRank,"Spade", i));
        }
        this.cards.add(new Card("Jack","Spade", 10));
        this.cards.add(new Card("Queen","Spade", 10));
        this.cards.add(new Card("King","Spade", 10));
        this.cards.add(new Card("Ace","Spade", 11));

        //all the Clubs
        for (int i = 2; i < 11; i++)
        {
            numRank = Integer.toString(i);
            this.cards.add(new Card(numRank,"Club", i));
        }
        this.cards.add(new Card("Jack","Club", 10));
        this.cards.add(new Card("Queen","Club", 10));
        this.cards.add(new Card("King","Club", 10));
        this.cards.add(new Card("Ace","Club", 11));

        //all the Hearts
        for (int i = 2; i < 11; i++)
        {
            numRank = Integer.toString(i);
            this.cards.add(new Card(numRank,"Heart", i));
        }
        this.cards.add(new Card("Jack", "Heart", 10));
        this.cards.add(new Card("Queen","Heart", 10));
        this.cards.add(new Card("King","Heart", 10));
        this.cards.add(new Card("Ace","Heart", 11));
    }

}
