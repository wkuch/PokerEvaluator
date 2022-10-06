import org.junit.jupiter.api.Test;
import org.kuchw.Card;
import org.kuchw.Hand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluationTest {

    private final Hand handWithLowStraightFlush = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.C, Card.Value.THREE),
            new Card(Card.Suit.C, Card.Value.FOUR),
            new Card(Card.Suit.C, Card.Value.FIVE),
            new Card(Card.Suit.C, Card.Value.SIX)
    });

    private final Hand royalFlush = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TEN),
            new Card(Card.Suit.C, Card.Value.JACK),
            new Card(Card.Suit.C, Card.Value.QUEEN),
            new Card(Card.Suit.C, Card.Value.KING),
            new Card(Card.Suit.C, Card.Value.ACE)
    });

    private final Hand handWithPair = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.D, Card.Value.TWO),
            new Card(Card.Suit.C, Card.Value.FOUR),
            new Card(Card.Suit.C, Card.Value.FIVE),
            new Card(Card.Suit.C, Card.Value.SIX)
    });

    private Hand handWithTwoPair = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.D, Card.Value.TWO),
            new Card(Card.Suit.C, Card.Value.FOUR),
            new Card(Card.Suit.D, Card.Value.FOUR),
            new Card(Card.Suit.C, Card.Value.SIX)
    });

    private Hand handWithFullHouse = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.D, Card.Value.TWO),
            new Card(Card.Suit.C, Card.Value.FOUR),
            new Card(Card.Suit.D, Card.Value.FOUR),
            new Card(Card.Suit.H, Card.Value.FOUR)
    });

    @Test
    public void validateEvaluation(){

        handWithLowStraightFlush.basicSelfEvaluation();
        assertEquals(true, handWithLowStraightFlush.isStraight());
        assertEquals(true, handWithLowStraightFlush.isFlush());
        assertEquals(0, handWithLowStraightFlush.getMultiples().size());
    }

}
