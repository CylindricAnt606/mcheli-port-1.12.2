/*    */ package mcheli.mcheli.command;
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
/*    */ public class MCH_PacketCommandSave
/*    */   extends MCH_Packet
/*    */ {
/* 18 */   public String str = "";
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMessageID() {
/* 23 */     return 536873729;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 32 */       this.str = data.readUTF();
/*    */     }
/* 34 */     catch (Exception e) {
/*    */       
/* 36 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 45 */       dos.writeUTF(this.str);
/*    */     }
/* 47 */     catch (IOException e) {
/*    */       
/* 49 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(String cmd) {
/* 55 */     mcheli.command.MCH_PacketCommandSave s = new mcheli.command.MCH_PacketCommandSave();
/* 56 */     s.str = cmd;
/* 57 */     W_Network.sendToServer((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\command\MCH_PacketCommandSave.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */