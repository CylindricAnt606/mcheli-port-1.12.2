/*    */ package mcheli.mcheli.command;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketTitle
/*    */   extends MCH_Packet
/*    */ {
/* 20 */   public IChatComponent chatComponent = null;
/* 21 */   public int showTime = 1;
/* 22 */   public int position = 0;
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMessageID() {
/* 27 */     return 268438272;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 36 */       this.chatComponent = IChatComponent.Serializer.func_150699_a(data.readUTF());
/* 37 */       this.showTime = data.readShort();
/* 38 */       this.position = data.readShort();
/*    */     }
/* 40 */     catch (Exception e) {
/*    */       
/* 42 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 51 */       dos.writeUTF(IChatComponent.Serializer.func_150696_a(this.chatComponent));
/* 52 */       dos.writeShort(this.showTime);
/* 53 */       dos.writeShort(this.position);
/*    */     }
/* 55 */     catch (IOException e) {
/*    */       
/* 57 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(IChatComponent chat, int showTime, int pos) {
/* 63 */     mcheli.command.MCH_PacketTitle s = new mcheli.command.MCH_PacketTitle();
/* 64 */     s.chatComponent = chat;
/* 65 */     s.showTime = showTime;
/* 66 */     s.position = pos;
/* 67 */     W_Network.sendToAllPlayers((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\command\MCH_PacketTitle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */