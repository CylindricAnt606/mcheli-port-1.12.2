/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketIndOpenScreen
/*    */   extends MCH_Packet
/*    */ {
/* 16 */   public int guiID = -1;
/*    */   
/*    */   public int getMessageID() {
/* 19 */     return 536872992;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 27 */       this.guiID = data.readInt();
/*    */     }
/* 29 */     catch (Exception e) {
/*    */       
/* 31 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 40 */       dos.writeInt(this.guiID);
/*    */     }
/* 42 */     catch (IOException e) {
/*    */       
/* 44 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(int gui_id) {
/* 50 */     if (gui_id < 0)
/*    */       return; 
/* 52 */     mcheli.MCH_PacketIndOpenScreen s = new mcheli.MCH_PacketIndOpenScreen();
/* 53 */     s.guiID = gui_id;
/* 54 */     W_Network.sendToServer((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_PacketIndOpenScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */