/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Packet;
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
/*    */ public class MCH_PacketNotifyWeaponID
/*    */   extends MCH_Packet
/*    */ {
/* 22 */   public int entityID_Ac = -1;
/* 23 */   public int seatID = -1;
/* 24 */   public int weaponID = -1;
/* 25 */   public short ammo = 0;
/* 26 */   public short restAmmo = 0;
/*    */   
/*    */   public int getMessageID() {
/* 29 */     return 268439601;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 37 */       this.entityID_Ac = data.readInt();
/* 38 */       this.seatID = data.readByte();
/* 39 */       this.weaponID = data.readByte();
/* 40 */       this.ammo = data.readShort();
/* 41 */       this.restAmmo = data.readShort();
/*    */     }
/* 43 */     catch (Exception e) {
/*    */       
/* 45 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 54 */       dos.writeInt(this.entityID_Ac);
/* 55 */       dos.writeByte(this.seatID);
/* 56 */       dos.writeByte(this.weaponID);
/* 57 */       dos.writeShort(this.ammo);
/* 58 */       dos.writeShort(this.restAmmo);
/*    */     }
/* 60 */     catch (IOException e) {
/*    */       
/* 62 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(Entity sender, int sid, int wid, int ammo, int rest_ammo) {
/* 68 */     mcheli.aircraft.MCH_PacketNotifyWeaponID s = new mcheli.aircraft.MCH_PacketNotifyWeaponID();
/*    */     
/* 70 */     s.entityID_Ac = W_Entity.getEntityId(sender);
/* 71 */     s.seatID = sid;
/* 72 */     s.weaponID = wid;
/* 73 */     s.ammo = (short)ammo;
/* 74 */     s.restAmmo = (short)rest_ammo;
/*    */     
/* 76 */     W_Network.sendToAllAround((W_PacketBase)s, sender, 150.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketNotifyWeaponID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */