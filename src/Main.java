import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        String Ranks[] = {"2", "3", "4"};
        int Values[] = {2, 3, 4};
        String Suits[] = {"Spades", "Hearts", "Diamonds", "Clubs"};
        Deck wholeDeck = new Deck(Ranks, Suits, Values);

        Player John = new Player("John", wholeDeck.getArrList());
        System.out.println(John.toString());
    }
}
