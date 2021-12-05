/*    */ package mcheli.mcheli.lweapon;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Packet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketLightWeaponPlayerControl
/*    */   extends MCH_Packet
/*    */ {
/*    */   public boolean useWeapon = false;
/* 23 */   public int useWeaponOption1 = 0;
/* 24 */   public int useWeaponOption2 = 0;
/* 25 */   public double useWeaponPosX = 0.0D;
/* 26 */   public double useWeaponPosY = 0.0D;
/* 27 */   public double useWeaponPosZ = 0.0D;
/* 28 */   public int cmpReload = 0;
/* 29 */   public int camMode = 0;
/*    */   
/*    */   public int getMessageID() {
/* 32 */     return 536936464;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 40 */       this.useWeapon = (data.readByte() != 0);
/* 41 */       if (this.useWeapon) {
/*    */         
/* 43 */         this.useWeaponOption1 = data.readInt();
/* 44 */         this.useWeaponOption2 = data.readInt();
/* 45 */         this.useWeaponPosX = data.readDouble();
/* 46 */         this.useWeaponPosY = data.readDouble();
/* 47 */         this.useWeaponPosZ = data.readDouble();
/*    */       } 
/* 49 */       this.cmpReload = data.readByte();
/* 50 */       this.camMode = data.readByte();
/*    */     }
/* 52 */     catch (Exception e) {
/*    */       
/* 54 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 64 */       dos.writeByte(this.useWeapon ? 1 : 0);
/* 65 */       if (this.useWeapon) {
/*    */         
/* 67 */         dos.writeInt(this.useWeaponOption1);
/* 68 */         dos.writeInt(this.useWeaponOption2);
/* 69 */         dos.writeDouble(this.useWeaponPosX);
/* 70 */         dos.writeDouble(this.useWeaponPosY);
/* 71 */         dos.writeDouble(this.useWeaponPosZ);
/*    */       } 
/* 73 */       dos.writeByte(this.cmpReload);
/* 74 */       dos.writeByte(this.camMode);
/*    */     }
/* 76 */     catch (IOException e) {
/*    */       
/* 78 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\lweapon\MCH_PacketLightWeaponPlayerControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */