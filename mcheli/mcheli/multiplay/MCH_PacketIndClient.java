/*    */ package mcheli.mcheli.multiplay;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketIndClient
/*    */   extends MCH_Packet
/*    */ {
/* 19 */   public int CmdID = -1;
/*    */   
/*    */   public int getMessageID() {
/* 22 */     return 268438032;
/*    */   }
/*    */ 
/*    */   
/*    */   public String CmdStr;
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 30 */       this.CmdID = data.readInt();
/* 31 */       this.CmdStr = data.readUTF();
/*    */     }
/* 33 */     catch (Exception e) {
/*    */       
/* 35 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 44 */       dos.writeInt(this.CmdID);
/* 45 */       dos.writeUTF(this.CmdStr);
/*    */     }
/* 47 */     catch (IOException e) {
/*    */       
/* 49 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(EntityPlayer player, int cmd_id, String str) {
/* 55 */     if (cmd_id <= 0)
/*    */       return; 
/* 57 */     mcheli.multiplay.MCH_PacketIndClient s = new mcheli.multiplay.MCH_PacketIndClient();
/* 58 */     s.CmdID = cmd_id;
/* 59 */     s.CmdStr = str;
/* 60 */     W_Network.sendToPlayer((W_PacketBase)s, player);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_PacketIndClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */