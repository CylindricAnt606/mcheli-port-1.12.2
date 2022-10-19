/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketNotifyLock
/*    */   extends MCH_Packet
/*    */ {
/* 19 */   public int entityID = -1;
/*    */   
/*    */   public int getMessageID() {
/* 22 */     return 536873984;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 30 */       this.entityID = data.readInt();
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
/* 43 */       dos.writeInt(this.entityID);
/*    */     }
/* 45 */     catch (IOException e) {
/*    */       
/* 47 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(Entity target) {
/* 53 */     if (target != null) {
/*    */       
/* 55 */       mcheli.MCH_PacketNotifyLock s = new mcheli.MCH_PacketNotifyLock();
/* 56 */       s.entityID = target.func_145782_y();
/* 57 */       W_Network.sendToServer((W_PacketBase)s);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void sendToPlayer(EntityPlayer entity) {
/* 63 */     if (entity instanceof net.minecraft.entity.player.EntityPlayerMP) {
/*    */       
/* 65 */       mcheli.MCH_PacketNotifyLock s = new mcheli.MCH_PacketNotifyLock();
/* 66 */       W_Network.sendToPlayer((W_PacketBase)s, entity);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_PacketNotifyLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */