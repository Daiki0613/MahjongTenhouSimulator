package MahjongSimulator;

import java.util.ArrayList;
import java.util.List;

public enum Pieces {
  m1, m2, m3, m4, m5, m6, m7, m8, m9,
  s1, s2, s3, s4, s5, s6, s7, s8, s9,
  p1, p2, p3, p4, p5, p6, p7, p8, p9,
  x1, x2, x3, x4, x5, x6, x7;

  public static List<List<Integer>> splitList(List<Integer> list) {
    List<List<Integer>> splitList = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      List<Integer> m = new ArrayList<>();
      switch (i) {
        case 0 -> { for (int j = 0; j < 9; j++) m.add(list.get(j)); }
        case 1 -> { for (int j = 9; j < 18; j++) m.add(list.get(j)); }
        case 2 -> { for (int j = 18; j < 27; j++) m.add(list.get(j)); }
        case 3 -> { for (int j = 27; j < 34; j++) m.add(list.get(j)); }
      }
      splitList.add(m);
    }
    return splitList;
  }
}
