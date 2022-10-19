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
/*    */ 
/*    */ public class MCH_PacketNotifyOnMountEntity
/*    */   extends MCH_Packet
/*    */ {
/* 21 */   public int entityID_Ac = -1;
/* 22 */   public int entityID_rider = -1;
/* 23 */   public int seatID = -1;
/*    */   
/*    */   public int getMessageID() {
/* 26 */     return 268439632;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 34 */       this.entityID_Ac = data.readInt();
/* 35 */       this.entityID_rider = data.readInt();
/* 36 */       this.seatID = data.readByte();
/*    */     }
/* 38 */     catch (Exception e) {
/*    */       
/* 40 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 49 */       dos.writeInt(this.entityID_Ac);
/* 50 */       dos.writeInt(this.entityID_rider);
/* 51 */       dos.writeByte(this.seatID);
/*    */     }
/* 53 */     catch (IOException e) {
/*    */       
/* 55 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(MCH_EntityAircraft ac, Entity rider, int seatId) {
/* 61 */     if (ac == null || rider == null)
/* 62 */       return;  Entity pilot = ac.getRiddenByEntity();
/* 63 */     if (!(pilot instanceof EntityPlayer) || pilot.field_70128_L)
/*    */       return; 
/* 65 */     mcheli.aircraft.MCH_PacketNotifyOnMountEntity s = new mcheli.aircraft.MCH_PacketNotifyOnMountEntity();
/*    */     
/* 67 */     s.entityID_Ac = W_Entity.getEntityId((Entity)ac);
/* 68 */     s.entityID_rider = W_Entity.getEntityId(rider);
/* 69 */     s.seatID = seatId;
/*    */     
/* 71 */     W_Network.sendToPlayer((W_PacketBase)s, (EntityPlayer)pilot);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketNotifyOnMountEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */