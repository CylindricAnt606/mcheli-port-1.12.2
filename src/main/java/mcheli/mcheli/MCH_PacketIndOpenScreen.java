 package mcheli.mcheli;

 import com.google.common.io.ByteArrayDataInput;
 import java.io.DataOutputStream;
 import java.io.IOException;
 import mcheli.mcheli.MCH_Packet;
 import mcheli.mcheli.wrapper.W_Network;
 import mcheli.mcheli.wrapper.W_PacketBase;




 public class MCH_PacketIndOpenScreen
   extends MCH_Packet
 {
   public int guiID = -1;

   public int getMessageID() {
     return 536872992;
   }




   public void readData(ByteArrayDataInput data) {
     try {
       this.guiID = data.readInt();
     }
     catch (Exception e) {

       e.printStackTrace();
     }
   }




   public void writeData(DataOutputStream dos) {
     try {
       dos.writeInt(this.guiID);
     }
     catch (IOException e) {

       e.printStackTrace();
     }
   }


   public static void send(int gui_id) {
     if (gui_id < 0)
       return;
     mcheli.MCH_PacketIndOpenScreen s = new mcheli.MCH_PacketIndOpenScreen();
     s.guiID = gui_id;
     W_Network.sendToServer((W_PacketBase)s);
   }
 }
