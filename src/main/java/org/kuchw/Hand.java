package org.kuchw;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {

    private final Card[] cards;

    private boolean isFlush = false;
    private boolean isStraight = false;
    private List<List<Card>> multiples = null;

    public Hand(Card[] cards) {

        this.cards = cards;

    }

    public boolean isFlush() {
        return isFlush;
    }

    public boolean isStraight() {
        return isStraight;
    }

    public List<List<Card>> getMultiples() {
        return multiples;
    }

    public void basicSelfEvaluation(){
        this.isFlush = this.evaluateFlush();
        this.isStraight = this.evaluateStraight();
        this.multiples = this.findMultiples();
    }

    private boolean evaluateFlush(){
        return Arrays.stream(cards).allMatch(card -> card.getSuit() == cards[0].getSuit());
    }

    private boolean evaluateStraight(){
        List <Integer> cardValues =  Arrays.stream(cards)
                .sorted(Comparator.reverseOrder())
                .map(card -> Rules.valueOrder.indexOf(card.getValue()))
                .collect(Collectors.toList());

        for(int i = 0; i < cardValues.size() - 2; i++){
            if(cardValues.get(i) - cardValues.get(i + 1) != 1){
                return false;
            }
        }

        return true;

    }

    private List<List<Card>> findMultiples(){

        return Arrays.stream(cards)
                .collect(Collectors.groupingBy(Card::getValue))
                .values()
                .stream()
                .filter(cards -> cards.size() > 1)
                .collect(Collectors.toList());

    }

}
