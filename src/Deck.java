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

    }

    public void shuffle()
    {
        Card inbetween;
        for (int i = cards.size() - 1; i > 0; i--)
        {
            int randNum = (int) ((Math.random() * i) + 1);
            inbetween = cards.get(i);
            cards.set(i, cards.get(randNum));
            cards.set(randNum, inbetween);
        }
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
    public ArrayList<Card> getCards()
    {
        return this.cards;
    }
}
