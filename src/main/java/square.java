import java.util.ArrayList;
import java.util.List;

public class square {

  public static enum data {
    Bomb, None;
  }

  private data status = data.None;
  private int surroundBombCount;
  private boolean openFlg = false;
  private List<String> surroundNoneSquare = new ArrayList<String>();

  public void setStatus(data status) {
    this.status = status;
  }

  public String display() {
    if (openFlg) {
      return Integer.toString(surroundBombCount);
    }
    return "*";
  }

  public data getStatus() {
    return status;
  }

  public boolean getOpenFlg() {
    return this.openFlg;
  }

  public void setSurroundBombCount(int surround) {
    this.surroundBombCount = surround;
  }

  public boolean checkBomb() {
    if (status.equals(data.Bomb)) {
      return true;
    }
    this.openFlg = true;
    return false;
  }

  public List<String> getSurroundNoneSquare() {
    return surroundNoneSquare;
  }

  public void setSurroundNoneSquare(String surroundNoneSquare) {
    this.surroundNoneSquare.add(surroundNoneSquare);
  }

  public int getSurroundBombCount() {
    return surroundBombCount;
  }
}