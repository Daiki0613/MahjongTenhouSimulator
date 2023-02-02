package MahjongSimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String arg[]) {
//    System.out.println(WinChecker.winCheck(piecesList));

    Game game = new Game();
    game.calculateAverageTrials(10);
  }

  public static List<Pieces> p1 = Arrays.asList(Pieces.m2, Pieces.m3, Pieces.m4, Pieces.m7, Pieces.m8, Pieces.m9, Pieces.s7,
        Pieces.s7, Pieces.p1, Pieces.p2, Pieces.p3, Pieces.p4, Pieces.p5, Pieces.p6);
  public static List<Pieces> p2 = Arrays.asList(Pieces.m1, Pieces.m9, Pieces.p1, Pieces.p9, Pieces.s1, Pieces.s9,
        Pieces.x1, Pieces.x2, Pieces.x3, Pieces.x4, Pieces.x5, Pieces.x6, Pieces.x7, Pieces.x3);

  public static List<Pieces> p3 = Arrays.asList(Pieces.m1, Pieces.m1, Pieces.m1, Pieces.m2, Pieces.m3, Pieces.m4,
        Pieces.m5, Pieces.m6, Pieces.m7, Pieces.m8, Pieces.m9, Pieces.m9, Pieces.m9, Pieces.m3);
}