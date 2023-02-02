package MahjongSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Game {

  public static final int HAND_SIZE = 14;
  List<Pieces> deck;

  public Game() {
    deck = makeDeck();
  }
  private List<Pieces> makeDeck() {
    List<Pieces> deck = new ArrayList<>();
    for (Pieces piece : Pieces.values()) {
      for (int i = 0; i < 4; i++) deck.add(piece);
    }
    Collections.shuffle(deck);
    return deck;
  }

  private List<Pieces> generateHand() {
    Collections.shuffle(deck);
    List<Pieces> hand = new ArrayList<>();
    for (int i = 0; i < HAND_SIZE; i++) {
      hand.add(deck.get(i));
    }
    return hand;
  }

  public boolean tryTenhou() {
    return tryTenhou(1);
  }

  public boolean tryTenhou(int trials) {
    int counter = 0;
    while (counter < trials) {
      counter++;
      List<Pieces> hand = generateHand();
      if (WinChecker.winCheck(hand)) {
        System.out.println("Congratulations!! You have won in " + counter + " trials!");
        System.out.println("Your winning hand is:");
        System.out.println(hand);
        return true;
      }
    }
    System.out.println("Unfortunately you did not win in " + trials + " trials");
    return false;
  }

  public boolean generateTenhou() {
    return tryTenhou(Integer.MAX_VALUE);
  }

  public void calculateAverageTrials() {
    calculateAverageTrials(50);
  }
  public void calculateAverageTrials(int maxWins) {
    long min = Integer.MAX_VALUE;
    long max = 0;
    long prev = 0;
    long counter = 0;
    long tenhouCount = 0;
    while (counter < Integer.MAX_VALUE) {
      counter++;
      List<Pieces> hand = generateHand();
      if (WinChecker.winCheck(hand)) {
        tenhouCount++;
        System.out.println("Won " + tenhouCount + " times! with " + hand);

        min = Math.min(counter - prev, min);
        max = Math.max(counter - prev, max);
        prev = counter;
      }
      if (tenhouCount >= maxWins) break;
    }
    System.out.println("You won " + tenhouCount + " times in " + counter + " trials!!");
    System.out.println("Your won once every " + counter / tenhouCount + " trials");
    System.out.println("Your shortest win was in " + min + " trials");
    System.out.println("Your longest win was in " + max + " trials");
  }
}
