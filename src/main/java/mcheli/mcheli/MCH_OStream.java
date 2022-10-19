package mcheli.mcheli;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MCH_OStream
  extends ByteArrayOutputStream {
  public int index = 0;

  public static final int SIZE = 30720;

  public void write(DataOutputStream dos) {
    try {
      int datasize;
      if (this.index + 30720 <= size()) {

        datasize = 30720;
      }
      else {

        datasize = size() - this.index;
      }
      dos.writeInt(this.index);
      dos.writeInt(datasize);
      dos.writeInt(size());
      dos.write(this.buf, this.index, datasize);
      this.index += datasize;
    }
    catch (IOException e) {

      e.printStackTrace();
    }
  }


  public boolean isDataEnd() {
    return (this.index >= size());
  }
}
