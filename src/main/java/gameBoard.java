import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class GameBoard {

  private Square gameBorad[][];
  private int maxSize;

  public GameBoard(int n) {
    // TODO Auto-generated constructor stub
    this.gameBorad = new Square[n][n];
    this.maxSize = n - 1;

    ArrayList<Integer> xlist = notOverlapRun(n);
    ArrayList<Integer> ylist = run(n);

    // 縦の列
    for (int i = 0; i < gameBorad.length; i++) {
      // 横の列
      for (int j = 0; j < gameBorad.length; j++) {
        gameBorad[i][j] = new Square();
      }
    }
    for (int i = 0; i < xlist.size(); i++) {
      gameBorad[xlist.get(i)][ylist.get(i)].setStatus(Square.data.Bomb);
      System.out.println("セットしました" + xlist.get(i) + ":" + ylist.get(i));
    }
  }

  public boolean turnOver(int x, int y) {

    gameBorad[x][y].setSurroundBombCount(checksurround(x, y));
    boolean checkResult = gameBorad[x][y].checkBomb();
    if (checkResult) {
      return false;
    }

    if (gameBorad[x][y].getSurroundBombCount() == 0) {
      List<String> surroundNoneList = gameBorad[x][y].getSurroundNoneSquare();
      for (int i = 0; i < surroundNoneList.size(); i++) {
        String[] xy = StringUtils.split(surroundNoneList.get(i), ",");
        int surroundX = Integer.parseInt(xy[0]);
        int surroundY = Integer.parseInt(xy[1]);
        gameBorad[surroundX][surroundY].setSurroundBombCount(checksurround(
            surroundX, surroundY));
        // 周囲のnotボム処理
        if (!gameBorad[surroundX][surroundY].getOpenFlg()
            && !gameBorad[surroundX][surroundY].checkBomb()) {
          for (int j = 0; j < gameBorad[surroundX][surroundY]
              .getSurroundNoneSquare().size(); j++) {
            if (gameBorad[surroundX][surroundY].getSurroundBombCount() == 0) {
              surroundNoneList.add(gameBorad[surroundX][surroundY]
                  .getSurroundNoneSquare().get(j));
            }
          }
        }
      }
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

  private int checksurround(int x, int y) {
    // 周りの値をチェック
    int count = 0;

    int xPlus1 = x + 1;
    int xMinus1 = x - 1;
    int yPlus1 = y + 1;
    int yMinus1 = y - 1;

    // 左列
    if (yMinus1 >= 0 && xMinus1 >= 0
        && gameBorad[xMinus1][yMinus1].getStatus().equals(Square.data.Bomb)) {
      count++;
    } else if (yMinus1 >= 0 && xMinus1 >= 0) {
      String surroundNoneSquare = xMinus1 + "," + yMinus1;
      gameBorad[x][y].setSurroundNoneSquare(surroundNoneSquare);
    }
    if (xMinus1 >= 0
        && gameBorad[xMinus1][y].getStatus().equals(Square.data.Bomb)) {
      count++;
    } else if (xMinus1 >= 0) {
      String surroundNoneSquare = xMinus1 + "," + y;
      gameBorad[x][y].setSurroundNoneSquare(surroundNoneSquare);
    }
    if (xMinus1 >= 0 && yPlus1 <= maxSize
        && gameBorad[xMinus1][yPlus1].getStatus().equals(Square.data.Bomb)) {
      count++;
    } else if (xMinus1 >= 0 && yPlus1 <= maxSize) {
      String surroundNoneSquare = xMinus1 + "," + yPlus1;
      gameBorad[x][y].setSurroundNoneSquare(surroundNoneSquare);
    }

    // 中心列
    if (yMinus1 >= 0
        && gameBorad[x][yMinus1].getStatus().equals(Square.data.Bomb)) {
      count++;
    } else if (yMinus1 >= 0) {
      String surroundNoneSquare = x + "," + yMinus1;
      gameBorad[x][y].setSurroundNoneSquare(surroundNoneSquare);
    }
    if (yPlus1 <= maxSize
        && gameBorad[x][yPlus1].getStatus().equals(Square.data.Bomb)) {
      count++;
    } else if (yPlus1 <= maxSize) {
      String surroundNoneSquare = x + "," + yPlus1;
      gameBorad[x][y].setSurroundNoneSquare(surroundNoneSquare);
    }

    // 右列
    if (xPlus1 <= maxSize && yMinus1 >= 0
        && gameBorad[xPlus1][yMinus1].getStatus().equals(Square.data.Bomb)) {
      count++;
    } else if (xPlus1 <= maxSize && yMinus1 >= 0) {
      String surroundNoneSquare = xPlus1 + "," + yMinus1;
      gameBorad[x][y].setSurroundNoneSquare(surroundNoneSquare);
    }
    if (xPlus1 <= maxSize
        && gameBorad[xPlus1][y].getStatus().equals(Square.data.Bomb)) {
      count++;
    } else if (xPlus1 <= maxSize) {
      String surroundNoneSquare = xPlus1 + "," + y;
      gameBorad[x][y].setSurroundNoneSquare(surroundNoneSquare);
    }
    if (yPlus1 <= maxSize && xPlus1 <= maxSize
        && gameBorad[xPlus1][yPlus1].getStatus().equals(Square.data.Bomb)) {
      count++;
    } else if (yPlus1 <= maxSize && xPlus1 <= maxSize) {
      String surroundNoneSquare = xPlus1 + "," + yPlus1;
      gameBorad[x][y].setSurroundNoneSquare(surroundNoneSquare);
    }
    return count;
  }

  private ArrayList<Integer> notOverlapRun(int n) {
    ArrayList<Integer> xlist = new ArrayList<Integer>();
    for (int i = 0; i < n; i++) {
      xlist.add(i);
    }
    return xlist;
  }

  private ArrayList<Integer> run(int n) {
    ArrayList<Integer> yList = new ArrayList<Integer>();

    // Randomクラスのインスタンス化
    Random rnd = new Random();

    for (int i = 0; i < n; i++) {
      yList.add(rnd.nextInt(n));
    }
    for (int i : yList) {
      System.out.println(yList.get(i));
    }
    return yList;

  }
}
