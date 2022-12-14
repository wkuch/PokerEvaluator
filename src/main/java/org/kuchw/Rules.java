package org.kuchw;

import java.util.List;

public class Rules {
    public final static List<Card.Value> valueOrder = List.of(
            Card.Value.TWO,
            Card.Value.THREE,
            Card.Value.FOUR,
            Card.Value.FIVE,
            Card.Value.SIX,
            Card.Value.SEVEN,
            Card.Value.EIGHT,
            Card.Value.NINE,
            Card.Value.TEN,
            Card.Value.JACK,
            Card.Value.QUEEN,
            Card.Value.KING,
            Card.Value.ACE
    );

    public static int getCardValueIndex(Card.Value value) {
        return valueOrder.indexOf(value);
    }

    public static Hand findBetterHand(Hand firstHand, Hand secondHand) {

        //Handle case when hand types are different
        for(int i = 0; i < firstHand.getHandType().length; i++) {
            int evaluation = firstHand.getHandType()[i] - secondHand.getHandType()[i];

            if(evaluation == 1){
                return firstHand;
            } else if(evaluation == -1){
                return secondHand;
            }

        }

        //Handle cases when hand types are the same
        if(firstHand.getHandType()[0] == 1 || firstHand.getHandType()[3] == 1 || firstHand.getHandType()[4] == 1){

            return handleFlushAndStraight(firstHand, secondHand);

        }

        if(firstHand.getHandType()[1] == 1 || firstHand.getHandType()[2] == 1 || firstHand.getHandType()[5] == 1){

            return handleFourOfAKindAndFullHouseAndThreeOfAKind(firstHand, secondHand);

        }

        if(firstHand.getHandType()[6] == 1){

            return handleTwoPair(firstHand, secondHand);

        }

        if(firstHand.getHandType()[7] == 1){

            Hand firstHand1 = handlePair(firstHand, secondHand);
            if (firstHand1 != null) return firstHand1;
        }

        return handleHighestCard(firstHand, secondHand);

    }

    private static Hand handleHighestCard(Hand firstHand, Hand secondHand) {
        for(int i = 0; i < firstHand.getHighestCards().size(); i++){
            if(getCardValueIndex(firstHand.getHighestCards().get(i).getValue()) > getCardValueIndex(secondHand.getHighestCards().get(i).getValue())){
                return firstHand;
            } else if(getCardValueIndex(firstHand.getHighestCards().get(i).getValue()) < getCardValueIndex(secondHand.getHighestCards().get(i).getValue())){
                return secondHand;
            }
        }
        return null;
    }

    private static Hand handlePair(Hand firstHand, Hand secondHand) {
        Card multiplesCardFirstHand = firstHand.getMultiples().get(0).get(0);
        Card multiplesCardSecondHand = secondHand.getMultiples().get(0).get(0);
        if(getCardValueIndex(multiplesCardFirstHand.getValue()) > getCardValueIndex(multiplesCardSecondHand.getValue())){
            return firstHand;
        } else if(getCardValueIndex(multiplesCardFirstHand.getValue()) < getCardValueIndex(multiplesCardSecondHand.getValue())){
            return secondHand;
        }
        return null;
    }

    private static Hand handleTwoPair(Hand firstHand, Hand secondHand) {
        Card firstPairCardFirstHand = firstHand.getMultiples().get(0).get(0);
        Card firstPairCardSecondHand = secondHand.getMultiples().get(0).get(0);
        Card secondPairCardFirstHand = secondHand.getMultiples().get(1).get(0);
        Card secondPairCardSecondHand = secondHand.getMultiples().get(1).get(0);


        if(getCardValueIndex(firstPairCardFirstHand.getValue()) > getCardValueIndex(firstPairCardSecondHand.getValue())){
            return firstHand;
        } else if(getCardValueIndex(firstPairCardFirstHand.getValue()) < getCardValueIndex(firstPairCardSecondHand.getValue())){
            return secondHand;
        } else if(getCardValueIndex(secondPairCardFirstHand.getValue()) > getCardValueIndex(secondPairCardSecondHand.getValue())){
            return firstHand;
        } else if(getCardValueIndex(secondPairCardFirstHand.getValue()) < getCardValueIndex(secondPairCardSecondHand.getValue())){
            return secondHand;
        } else if (getCardValueIndex(firstHand.getHighestCards().get(0).getValue()) > getCardValueIndex(secondHand.getHighestCards().get(0).getValue())){
            return firstHand;
        } else if (getCardValueIndex(firstHand.getHighestCards().get(0).getValue()) < getCardValueIndex(secondHand.getHighestCards().get(0).getValue())){
            return secondHand;
        } else {
            return null;
        }
    }

    private static Hand handleFourOfAKindAndFullHouseAndThreeOfAKind(Hand firstHand, Hand secondHand) {
        Card multiplesCardFirstHand = firstHand.getMultiples().get(0).get(0);
        Card multiplesCardSecondHand = secondHand.getMultiples().get(0).get(0);

        if(getCardValueIndex(multiplesCardFirstHand.getValue()) > getCardValueIndex(multiplesCardSecondHand.getValue())){
            return firstHand;
        } else if(getCardValueIndex(multiplesCardFirstHand.getValue()) < getCardValueIndex(multiplesCardSecondHand.getValue())){
            return secondHand;
        } else {
            return null;
        }
    }

    private static Hand handleFlushAndStraight(Hand firstHand, Hand secondHand) {
        if(getCardValueIndex(firstHand.getCards()[0].getValue()) > getCardValueIndex(secondHand.getCards()[0].getValue())){
            return firstHand;
        } else if(getCardValueIndex(firstHand.getCards()[0].getValue()) < getCardValueIndex(secondHand.getCards()[0].getValue())){
            return secondHand;
        } else {
            return null;
        }
    }
}
