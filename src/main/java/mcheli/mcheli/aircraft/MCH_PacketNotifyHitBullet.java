/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.wrapper.W_Entity;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketNotifyHitBullet
/*    */   extends MCH_Packet
/*    */ {
/* 20 */   public int entityID_Ac = -1;
/*    */   
/*    */   public int getMessageID() {
/* 23 */     return 268439602;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 31 */       this.entityID_Ac = data.readInt();
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
/* 44 */       dos.writeInt(this.entityID_Ac);
/*    */     }
/* 46 */     catch (IOException e) {
/*    */       
/* 48 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(MCH_EntityAircraft ac, EntityPlayer rider) {
/* 54 */     if (rider == null || rider.field_70128_L)
/*    */       return; 
/* 56 */     mcheli.aircraft.MCH_PacketNotifyHitBullet s = new mcheli.aircraft.MCH_PacketNotifyHitBullet();
/* 57 */     s.entityID_Ac = (ac != null && !ac.field_70128_L) ? W_Entity.getEntityId((Entity)ac) : -1;
/* 58 */     W_Network.sendToPlayer((W_PacketBase)s, rider);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketNotifyHitBullet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */