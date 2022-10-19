/*    */ package mcheli.mcheli.gltd;
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
/*    */ 
/*    */ public class MCH_PacketGLTDPlayerControl
/*    */   extends MCH_Packet
/*    */ {
/* 23 */   public byte switchCameraMode = -1;
/* 24 */   public byte switchWeapon = -1;
/*    */   public boolean useWeapon = false;
/* 26 */   public int useWeaponOption1 = 0;
/* 27 */   public int useWeaponOption2 = 0;
/* 28 */   public double useWeaponPosX = 0.0D;
/* 29 */   public double useWeaponPosY = 0.0D;
/* 30 */   public double useWeaponPosZ = 0.0D;
/*    */   public boolean unmount = false;
/*    */   
/*    */   public int getMessageID() {
/* 34 */     return 536887312;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 42 */       this.switchCameraMode = data.readByte();
/* 43 */       this.switchWeapon = data.readByte();
/* 44 */       this.useWeapon = (data.readByte() != 0);
/* 45 */       if (this.useWeapon) {
/*    */         
/* 47 */         this.useWeaponOption1 = data.readInt();
/* 48 */         this.useWeaponOption2 = data.readInt();
/* 49 */         this.useWeaponPosX = data.readDouble();
/* 50 */         this.useWeaponPosY = data.readDouble();
/* 51 */         this.useWeaponPosZ = data.readDouble();
/*    */       } 
/* 53 */       this.unmount = (data.readByte() != 0);
/*    */     }
/* 55 */     catch (Exception e) {
/*    */       
/* 57 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 67 */       dos.writeByte(this.switchCameraMode);
/* 68 */       dos.writeByte(this.switchWeapon);
/* 69 */       dos.writeByte(this.useWeapon ? 1 : 0);
/* 70 */       if (this.useWeapon) {
/*    */         
/* 72 */         dos.writeInt(this.useWeaponOption1);
/* 73 */         dos.writeInt(this.useWeaponOption2);
/* 74 */         dos.writeDouble(this.useWeaponPosX);
/* 75 */         dos.writeDouble(this.useWeaponPosY);
/* 76 */         dos.writeDouble(this.useWeaponPosZ);
/*    */       } 
/* 78 */       dos.writeByte(this.unmount ? 1 : 0);
/*    */     }
/* 80 */     catch (IOException e) {
/*    */       
/* 82 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gltd\MCH_PacketGLTDPlayerControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */