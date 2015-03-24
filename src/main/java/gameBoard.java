import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class gameBoard {

  private square gameBorad[][];
  private int maxSize;

  public gameBoard(int n) {
    // TODO Auto-generated constructor stub
    this.gameBorad = new square[n][n];
    this.maxSize = n - 1;

    ArrayList<Integer> xlist = notOverlapRun(n);
    ArrayList<Integer> ylist = run(n);

    // 縦の列
    for (int i = 0; i < gameBorad.length; i++) {
      // 横の列
      for (int j = 0; j < gameBorad.length; j++) {
        if (xlist.iterator().next() == i || ylist.iterator().next() == j) {
          gameBorad[i][j] = new square(square.data.Bomb);
          // System.out.println("ボムの位置" + ": x=" + i + "" + ": y=" + j);
          continue;
        }
        gameBorad[i][j] = new square(square.data.None);
      }
    }
  }

  public ArrayList<Integer> notOverlapRun(int n) {
    ArrayList<Integer> xlist = new ArrayList<Integer>();
    for (int i = 0; i <= n; i++) {
      xlist.add(i);
    }
    // 乱数生成
    Collections.shuffle(xlist);
    return xlist;
  }

  private ArrayList<Integer> run(int n) {
    ArrayList<Integer> yList = new ArrayList<Integer>();

    // Randomクラスのインスタンス化
    Random rnd = new Random();

    for (int i = 0; i <= n; i++) {
      yList.add(rnd.nextInt(n + 1));
    }
    return yList;

  }

  public boolean checkBomb(int x, int y) {
    // 周りの値をチェック
    int count = 0;

    int xPlus1 = x + 1;
    int xMinus1 = x - 1;
    int yPlus1 = y + 1;
    int yMinus1 = y - 1;

    // 左列
    if (yMinus1 >= 0 && xMinus1 >= 0
        && gameBorad[xMinus1][yMinus1].getStatus().equals(square.data.Bomb)) {
      count++;
    }
    if (xMinus1 >= 0
        && gameBorad[xMinus1][y].getStatus().equals(square.data.Bomb)) {
      count++;
    }
    if (xMinus1 >= 0 && yPlus1 <= maxSize
        && gameBorad[xMinus1][yPlus1].getStatus().equals(square.data.Bomb)) {
      count++;
    }
    // 中心列
    if (yMinus1 >= 0
        && gameBorad[x][yMinus1].getStatus().equals(square.data.Bomb)) {
      count++;
    }
    if (yPlus1 <= maxSize
        && gameBorad[x][yPlus1].getStatus().equals(square.data.Bomb)) {
      count++;
    }
    // 右列
    if (xPlus1 <= maxSize && yMinus1 >= 0
        && gameBorad[xPlus1][yMinus1].getStatus().equals(square.data.Bomb)) {
      count++;
    }
    if (xPlus1 <= maxSize
        && gameBorad[xPlus1][y].getStatus().equals(square.data.Bomb)) {
      count++;
    }
    if (yPlus1 <= maxSize && xPlus1 <= maxSize
        && gameBorad[xPlus1][yPlus1].getStatus().equals(square.data.Bomb)) {
      count++;
    }

    boolean checkResult = gameBorad[x][y].turnOver(count);
    if (!checkResult) {
      return false;
    }
    return true;
  }

  public void display() {
    System.out.println("-----------------列を表示-----------------");
    // 縦の列表示
    for (int i = 0; i < gameBorad.length; i++) {
      // 横の列表示
      for (int j = 0; j < gameBorad[0].length; j++) {
        System.out.print(gameBorad[i][j].display());
        System.out.print(" ");
      }
      System.out.println();
    }
  }
}
