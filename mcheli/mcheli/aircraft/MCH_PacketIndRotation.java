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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketIndRotation
/*    */   extends MCH_Packet
/*    */ {
/* 23 */   public int entityID_Ac = -1;
/* 24 */   public float yaw = 0.0F;
/* 25 */   public float pitch = 0.0F;
/* 26 */   public float roll = 0.0F;
/*    */   public boolean rollRev = false;
/*    */   
/*    */   public int getMessageID() {
/* 30 */     return 536875062;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 38 */       this.entityID_Ac = data.readInt();
/* 39 */       this.yaw = data.readFloat();
/* 40 */       this.pitch = data.readFloat();
/* 41 */       this.roll = data.readFloat();
/* 42 */       this.rollRev = (data.readByte() != 0);
/*    */     }
/* 44 */     catch (Exception e) {
/*    */       
/* 46 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 55 */       dos.writeInt(this.entityID_Ac);
/* 56 */       dos.writeFloat(this.yaw);
/* 57 */       dos.writeFloat(this.pitch);
/* 58 */       dos.writeFloat(this.roll);
/* 59 */       dos.writeByte(this.rollRev ? 1 : 0);
/*    */     }
/* 61 */     catch (IOException e) {
/*    */       
/* 63 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(MCH_EntityAircraft ac) {
/* 69 */     if (ac == null)
/*    */       return; 
/* 71 */     mcheli.aircraft.MCH_PacketIndRotation s = new mcheli.aircraft.MCH_PacketIndRotation();
/*    */     
/* 73 */     s.entityID_Ac = W_Entity.getEntityId((Entity)ac);
/* 74 */     s.yaw = ac.getRotYaw();
/* 75 */     s.pitch = ac.getRotPitch();
/* 76 */     s.roll = ac.getRotRoll();
/* 77 */     s.rollRev = ac.aircraftRollRev;
/*    */     
/* 79 */     W_Network.sendToServer((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketIndRotation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */