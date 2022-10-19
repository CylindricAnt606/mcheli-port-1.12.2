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
/*    */ public class MCH_PacketIndNotifyAmmoNum
/*    */   extends MCH_Packet
/*    */ {
/* 19 */   public int entityID_Ac = -1;
/* 20 */   public byte weaponID = -1;
/*    */   
/*    */   public int getMessageID() {
/* 23 */     return 536875061;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 31 */       this.entityID_Ac = data.readInt();
/* 32 */       this.weaponID = data.readByte();
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
/* 45 */       dos.writeInt(this.entityID_Ac);
/* 46 */       dos.writeByte(this.weaponID);
/*    */     }
/* 48 */     catch (IOException e) {
/*    */       
/* 50 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(MCH_EntityAircraft ac, int wid) {
/* 56 */     mcheli.aircraft.MCH_PacketIndNotifyAmmoNum s = new mcheli.aircraft.MCH_PacketIndNotifyAmmoNum();
/*    */     
/* 58 */     s.entityID_Ac = W_Entity.getEntityId((Entity)ac);
/* 59 */     s.weaponID = (byte)wid;
/*    */     
/* 61 */     W_Network.sendToServer((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketIndNotifyAmmoNum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */