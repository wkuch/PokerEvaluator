package org.kuchw;

public class Card implements Comparable<Card> {

    private final Suit suit;
    private final Value value;

    public enum Suit{ C, D, H, S }

    public enum Value{ TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }

    public Card(Suit suit, Value value){
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public int compareTo(Card other) {
        if(this.value == other.value) {
            return 0;
        }

        if(Rules.valueOrder.indexOf(this.value) > Rules.valueOrder.indexOf(other.value)) {
            return 1;
        }

        return -1;
    }
}
