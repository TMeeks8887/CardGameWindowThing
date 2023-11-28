import java.util.ArrayList;

public class Deck
{

    private ArrayList<Card> cards;

    public Deck ()
    {
        this.cards = new ArrayList<>();
        String numRank;
        //all the Diamonds
        for (int i = 2; i < 11; i++)
        {
            numRank = Integer.toString(i);
            this.cards.add(new Card("Diamond", i, numRank));
        }
        this.cards.add(new Card("Diamond", 10, "Jack"));
        this.cards.add(new Card("Diamond", 10, "Queen"));
        this.cards.add(new Card("Diamond", 10, "King"));
        this.cards.add(new Card("Diamond", 11, "Ace"));

        //all the Spades
        for (int i = 2; i < 11; i++)
        {
            numRank = Integer.toString(i);
            this.cards.add(new Card("Spade", i, numRank));
        }
        this.cards.add(new Card("Spade", 10, "Jack"));
        this.cards.add(new Card("Spade", 10, "Queen"));
        this.cards.add(new Card("Spade", 10, "King"));
        this.cards.add(new Card("Spade", 11, "Ace"));

        //all the Clubs
        for (int i = 2; i < 11; i++)
        {
            numRank = Integer.toString(i);
            this.cards.add(new Card("Club", i, numRank));
        }
        this.cards.add(new Card("Club", 10, "Jack"));
        this.cards.add(new Card("Club", 10, "Queen"));
        this.cards.add(new Card("Club", 10, "King"));
        this.cards.add(new Card("Club", 11, "Ace"));

        //all the Hearts
        for (int i = 2; i < 11; i++)
        {
            numRank = Integer.toString(i);
            this.cards.add(new Card("Heart", i, numRank));
        }
        this.cards.add(new Card("Heart", 10, "Jack"));
        this.cards.add(new Card("Heart", 10, "Queen"));
        this.cards.add(new Card("Heart", 10, "King"));
        this.cards.add(new Card("Heart", 11, "Ace"));
    }
    public ArrayList<Card> getCards()
    {
        return this.cards;
    }
}
