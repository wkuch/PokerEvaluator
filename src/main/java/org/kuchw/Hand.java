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

    private List<Card> highestCards = null;

    private final int[] handType = new int[]{0,0,0,0,0,0,0,0};

    public Hand(Card[] cards) {

        this.cards = cards;
        this.selfEvaluation();
    }

    private void selfEvaluation(){
        this.basicSelfEvaluation();
        this.determineHandType();
    }

    private void basicSelfEvaluation(){
        this.isFlush = this.evaluateFlush();
        this.isStraight = this.evaluateStraight();
        this.multiples = this.findMultiples();
        this.highestCards = this.findHighestCards();
    }

    /**
     * The type of the hand is saved in an int array with 8 elements where each element is either 0 or 1.
     * Each element represents a different hand type and the value of the element is 1 if the hand is of that type.
     * The order of the elements is as follows:
     * index    - type
     * 0        - Straight Flush
     * 1        - Four of a Kind
     * 2        - Full House
     * 3        - Flush
     * 4        - Straight
     * 5        - Three of a Kind
     * 6        - Two Pair
     * 7        - Pair
     */
    private void determineHandType(){
        if(this.isStraight && this.isFlush){
            this.handType[0] = 1;
        }
        if(this.multiples.size() > 0 && this.multiples.get(0).size() == 4){
            this.handType[1] = 1;
        }
        if(this.multiples.size() > 1 && this.multiples.get(0).size() == 3 && this.multiples.get(1).size() == 2){
            this.handType[2] = 1;
        }
        if(this.isFlush){
            this.handType[3] = 1;
        }
        if(this.isStraight){
            this.handType[4] = 1;
        }
        if(this.multiples.size() > 0 && this.multiples.get(0).size() == 3){
            this.handType[5] = 1;
        }
        if(this.multiples.size() > 1 && this.multiples.get(0).size() == 2 && this.multiples.get(1).size() == 2){
            this.handType[6] = 1;
        }
        if(this.multiples.size() > 0 && this.multiples.get(0).size() == 2){
            this.handType[7] = 1;
        }

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
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.groupingBy(Card::getValue))
                .values()
                .stream()
                .filter(cards -> cards.size() > 1)
                .sorted((m1, m2) -> m2.size() - m1.size())
                .collect(Collectors.toList());

    }

    private List<Card> findHighestCards(){
        return Arrays.stream(cards)
                .sorted(Comparator.reverseOrder())
                .filter(cards -> this.multiples.stream().noneMatch(m -> m.contains(cards)))
                .collect(Collectors.toList());
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

    public int[] getHandType() {
        return handType;
    }

    public Card[] getCards() {
        return cards;
    }

    public List<Card> getHighestCards() {
        return highestCards;
    }
}
