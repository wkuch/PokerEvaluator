import org.junit.jupiter.api.Test;
import org.kuchw.Card;
import org.kuchw.Hand;
import org.kuchw.Rules;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluationTest {

    private final Hand handWithLowStraightFlush = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.C, Card.Value.THREE),
            new Card(Card.Suit.C, Card.Value.FOUR),
            new Card(Card.Suit.C, Card.Value.FIVE),
            new Card(Card.Suit.C, Card.Value.SIX)
    });

    private final Hand handWithRoyalFlush = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TEN),
            new Card(Card.Suit.C, Card.Value.JACK),
            new Card(Card.Suit.C, Card.Value.QUEEN),
            new Card(Card.Suit.C, Card.Value.KING),
            new Card(Card.Suit.C, Card.Value.ACE)
    });

    private final Hand handWithFourOfAKind = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.D, Card.Value.TWO),
            new Card(Card.Suit.H, Card.Value.TWO),
            new Card(Card.Suit.S, Card.Value.TWO),
            new Card(Card.Suit.C, Card.Value.ACE)
    });

    private final Hand handWithFourOfAKindHighCard = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.ACE),
            new Card(Card.Suit.D, Card.Value.ACE),
            new Card(Card.Suit.H, Card.Value.ACE),
            new Card(Card.Suit.S, Card.Value.ACE),
            new Card(Card.Suit.C, Card.Value.KING)
    });

    private final Hand handWithFlushOne = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.C, Card.Value.THREE),
            new Card(Card.Suit.C, Card.Value.FOUR),
            new Card(Card.Suit.C, Card.Value.FIVE),
            new Card(Card.Suit.C, Card.Value.ACE)
    });

    private final Hand handWithFlushTwo = new Hand(new Card[]{
            new Card(Card.Suit.S, Card.Value.TWO),
            new Card(Card.Suit.S, Card.Value.THREE),
            new Card(Card.Suit.S, Card.Value.FOUR),
            new Card(Card.Suit.S, Card.Value.FIVE),
            new Card(Card.Suit.S, Card.Value.ACE)
    });

    private final Hand handWithPair = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.D, Card.Value.TWO),
            new Card(Card.Suit.C, Card.Value.FOUR),
            new Card(Card.Suit.C, Card.Value.FIVE),
            new Card(Card.Suit.C, Card.Value.SIX)
    });

    private final Hand handWithTwoPairLow = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.D, Card.Value.TWO),
            new Card(Card.Suit.C, Card.Value.FOUR),
            new Card(Card.Suit.D, Card.Value.FOUR),
            new Card(Card.Suit.C, Card.Value.SIX)
    });

    private final Hand handWithTwoPairHigh = new Hand(new Card[]{
            new Card(Card.Suit.H, Card.Value.TWO),
            new Card(Card.Suit.S, Card.Value.TWO),
            new Card(Card.Suit.H, Card.Value.FOUR),
            new Card(Card.Suit.S, Card.Value.FOUR),
            new Card(Card.Suit.C, Card.Value.JACK)
    });

    private final Hand handWithFullHouse = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.D, Card.Value.TWO),
            new Card(Card.Suit.C, Card.Value.FOUR),
            new Card(Card.Suit.D, Card.Value.FOUR),
            new Card(Card.Suit.H, Card.Value.FOUR)
    });

    private final Hand handWithFullHouseHigh = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.D, Card.Value.TWO),
            new Card(Card.Suit.C, Card.Value.ACE),
            new Card(Card.Suit.D, Card.Value.ACE),
            new Card(Card.Suit.H, Card.Value.ACE)
    });

    private final Hand handWithHighCardJack = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.D, Card.Value.SEVEN),
            new Card(Card.Suit.C, Card.Value.FOUR),
            new Card(Card.Suit.D, Card.Value.FIVE),
            new Card(Card.Suit.H, Card.Value.JACK)
    });

    private final Hand handWithHighCardAce = new Hand(new Card[]{
            new Card(Card.Suit.C, Card.Value.TWO),
            new Card(Card.Suit.D, Card.Value.SEVEN),
            new Card(Card.Suit.C, Card.Value.FOUR),
            new Card(Card.Suit.D, Card.Value.FIVE),
            new Card(Card.Suit.H, Card.Value.ACE)
    });

    @Test
    public void validateBasicEvaluation(){

        assertTrue(handWithLowStraightFlush.isStraight());
        assertTrue(handWithLowStraightFlush.isFlush());
        assertEquals(0, handWithLowStraightFlush.getMultiples().size());
        assertEquals(handWithFourOfAKind.getHighestCards().get(0).getValue(), Card.Value.ACE);
        assertEquals(handWithFourOfAKind.getHighestCards().get(0).getSuit(), Card.Suit.C);


    }

    @Test
    public void validateHandComparison(){

        //some tests for different hand types
        assertEquals(Rules.findBetterHand(handWithFullHouse, handWithTwoPairHigh), handWithFullHouse);
        assertEquals(Rules.findBetterHand(handWithLowStraightFlush, handWithFullHouse),handWithLowStraightFlush);
        assertEquals(Rules.findBetterHand(handWithPair, handWithTwoPairHigh), handWithTwoPairHigh);
        assertEquals(Rules.findBetterHand(handWithLowStraightFlush, handWithTwoPairHigh), handWithLowStraightFlush);

        //some tests for same hand types
        assertEquals(Rules.findBetterHand(handWithLowStraightFlush, handWithRoyalFlush), handWithRoyalFlush);
        assertEquals(Rules.findBetterHand(handWithFourOfAKind, handWithFourOfAKindHighCard), handWithFourOfAKindHighCard);
        assertEquals(Rules.findBetterHand(handWithFullHouse, handWithFullHouseHigh), handWithFullHouseHigh);
        assertEquals(Rules.findBetterHand(handWithTwoPairLow, handWithTwoPairHigh), handWithTwoPairHigh);
        assertEquals(Rules.findBetterHand(handWithHighCardJack, handWithHighCardAce), handWithHighCardAce);

        //Hand with same value should return null when compared
        assertNull(Rules.findBetterHand(handWithFlushOne, handWithFlushTwo));
        assertNull(Rules.findBetterHand(handWithFlushOne, handWithFlushTwo));

    }


}
