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
/*    */ public class MCH_PacketIndReload
/*    */   extends MCH_Packet
/*    */ {
/* 20 */   public int entityID_Ac = -1;
/* 21 */   public int weaponID = -1;
/*    */   
/*    */   public int getMessageID() {
/* 24 */     return 536875059;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 32 */       this.entityID_Ac = data.readInt();
/* 33 */       this.weaponID = data.readByte();
/*    */     }
/* 35 */     catch (Exception e) {
/*    */       
/* 37 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 46 */       dos.writeInt(this.entityID_Ac);
/* 47 */       dos.writeByte(this.weaponID);
/*    */     }
/* 49 */     catch (IOException e) {
/*    */       
/* 51 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(MCH_EntityAircraft ac, int weaponId) {
/* 57 */     if (ac == null)
/*    */       return; 
/* 59 */     mcheli.aircraft.MCH_PacketIndReload s = new mcheli.aircraft.MCH_PacketIndReload();
/*    */     
/* 61 */     s.entityID_Ac = W_Entity.getEntityId((Entity)ac);
/* 62 */     s.weaponID = weaponId;
/*    */     
/* 64 */     W_Network.sendToServer((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketIndReload.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */