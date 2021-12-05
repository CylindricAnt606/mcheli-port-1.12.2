/*    */ package mcheli.mcheli.multiplay;
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
/*    */ 
/*    */ 
/*    */ public class MCH_PacketIndMultiplayCommand
/*    */   extends MCH_Packet
/*    */ {
/* 18 */   public int CmdID = -1;
/*    */   
/*    */   public int getMessageID() {
/* 21 */     return 536873088;
/*    */   }
/*    */ 
/*    */   
/*    */   public String CmdStr;
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 29 */       this.CmdID = data.readInt();
/* 30 */       this.CmdStr = data.readUTF();
/*    */     }
/* 32 */     catch (Exception e) {
/*    */       
/* 34 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 43 */       dos.writeInt(this.CmdID);
/* 44 */       dos.writeUTF(this.CmdStr);
/*    */     }
/* 46 */     catch (IOException e) {
/*    */       
/* 48 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(int cmd_id, String str) {
/* 54 */     if (cmd_id <= 0)
/*    */       return; 
/* 56 */     mcheli.multiplay.MCH_PacketIndMultiplayCommand s = new mcheli.multiplay.MCH_PacketIndMultiplayCommand();
/* 57 */     s.CmdID = cmd_id;
/* 58 */     s.CmdStr = str;
/* 59 */     W_Network.sendToServer((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_PacketIndMultiplayCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */