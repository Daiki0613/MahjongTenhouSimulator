package MahjongSimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinChecker {
  public static boolean winCheck(List<Pieces> piecesList) {
    if (piecesList == null || piecesList.size() != 14) return false;
    piecesList.sort(Pieces::compareTo);
    return thirteenOrphans(piecesList) || sevenPairs(piecesList) || normalWin(piecesList);
  }

  private static boolean nineGates(List<Pieces> piecesList) {
    List<Integer> list = new ArrayList<>(Collections.nCopies(Pieces.values().length, 0));
    for (Pieces piece : piecesList) list.set(piece.ordinal(), list.get(piece.ordinal()) + 1);

    List<Integer> winningShape = Arrays.asList(3,1,1,1,1,1,1,1,3);
    for (List<Integer> type : Pieces.splitList(list)) {
      if (type.size() != 9) continue;

      int sum = 0;
      for (int i = 0; i < type.size(); i++) sum += winningShape.get(i);
      if (sum != 14) continue;

      boolean win = true;
      for (int i = 0; i < type.size(); i++) {
        if (type.get(i) < winningShape.get(i)) {
          win = false;
          break;
        }
      }
      if (win) return true;

    }
    return false;
  }

  private static boolean thirteenOrphans(List<Pieces> piecesList) {
    Set<Pieces> handSet = new HashSet<>(piecesList);
    Set<Pieces> thirteenSet = Stream.of("m1", "m9", "s1", "s9", "p1", "p9", "x1", "x2", "x3", "x4",
        "x5", "x6", "x7").map(Pieces::valueOf).collect(Collectors.toSet());
    handSet.retainAll(thirteenSet);
    return handSet.size() == 13;
  }

  private static boolean sevenPairs(List<Pieces> piecesList) {
    return piecesList.stream().distinct().filter(x -> Collections.frequency(piecesList, x) == 2)
        .count() == 7;
  }

  private static boolean normalWin(List<Pieces> piecesList) {
    List<Integer> list = new ArrayList<>(Collections.nCopies(Pieces.values().length, 0));
    for (Pieces piece : piecesList) list.set(piece.ordinal(), list.get(piece.ordinal()) + 1);

    for (Pieces pair : piecesList.stream().distinct()
        .filter(x -> Collections.frequency(piecesList, x) >= 2).toList()) {
      list.set(pair.ordinal(), list.get(pair.ordinal()) - 2); // remove jantou

      boolean win = true;
      for (List<Integer> m : Pieces.splitList(list)) {
        if (!mentsuChecker(m)) { win = false; break; }
      }
      if (win) return true;
      list.set(pair.ordinal(), list.get(pair.ordinal()) + 2); // place back jantou
    }
    return false;
  }

  private static boolean suujiChecker(List<Integer> intList) {
    for (int i = 0; i < 9; i++) intList.set(i, intList.get(i) % 3);
    for (int i = 0; i < 7; i++) {
      if (intList.get(i) < 0) { return false; }
      intList.set(i + 1, intList.get(i + 1) - intList.get(i));
      intList.set(i + 2, intList.get(i + 2) - intList.get(i));
      intList.set(i, 0);
    }
    if (intList.get(7) != 0 || intList.get(8) != 0) return false;
    return true;
  }

  private static boolean jihaiChecker(List<Integer> intList) {
    for (int i = 0; i < intList.size(); i++) {
      if (intList.get(i) % 3 != 0) return false;
    }
    return true;
  }

  private static boolean mentsuChecker(List<Integer> intList) {
    // size check
    int sum = 0;
    for (int i = 0; i < intList.size(); i++) sum += intList.get(i);
    if (sum == 0) return true;
    if (sum % 3 != 0) return false;

    if (intList.size() == 7) return jihaiChecker(intList);
    else return suujiChecker(intList);
  }

}
