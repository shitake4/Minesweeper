import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

public class GameController {

  public static void main(String[] args) {
    try {
      System.out.println("ゲームを開始します");
      GameBoard board = new GameBoard(10);
      board.display();

      BufferedReader stdReader = new BufferedReader(new InputStreamReader(
          System.in));
      System.out.print("[x:yで入力]INPUT : ");
      String line;
      while ((line = stdReader.readLine()) != null) { // ユーザの一行入力を待つ
        if (line.equals(""))
          line = "<空文字>";

        String[] inline = StringUtils.split(line, ":");
        int x = Integer.parseInt(inline[0]);
        int y = Integer.parseInt(inline[1]);

        System.out.println("input: '" + line + "'");
        boolean gameResult = board.turnOver(x, y);
        if (!gameResult) {
          System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
          System.out.println("xxxxxxxxxxxx   GameOver   xxxxxxxxxxxx");
          System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
          stdReader.close();
          System.out.println("\nPROGRAM END");
        } else {
          board.display();
          System.out.print("\nINPUT : ");
        }
      }
    } catch (Exception e) {
      e.getStackTrace();
      System.exit(-1); // プログラムを終了
    }
  }

}
