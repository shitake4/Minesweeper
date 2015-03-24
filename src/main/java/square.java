

public class square {

  public static enum data {
    Bomb, None;
  }

  private data status;
  private int surround;
  private boolean openFlg = false;

  public square(data status) {
    // TODO Auto-generated constructor stub
    this.status = status;
  }

  public String display() {
    if (openFlg) {
      return Integer.toString(surround);
    }
    return "*";
  }

  public data getStatus() {
    return status;
  }

  public boolean turnOver(int surround) {
    if (status.equals(data.Bomb)) {
      return false;
    }
    this.surround = surround;
    this.openFlg = true;
    return true;
  }
}