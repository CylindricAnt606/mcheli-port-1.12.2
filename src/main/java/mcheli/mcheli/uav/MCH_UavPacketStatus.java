/*    */ package mcheli.mcheli.uav;
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
/*    */ public class MCH_UavPacketStatus
/*    */   extends MCH_Packet
/*    */ {
/* 18 */   public byte posUavX = 0;
/* 19 */   public byte posUavY = 0;
/* 20 */   public byte posUavZ = 0;
/*    */   public boolean continueControl = false;
/*    */   
/*    */   public int getMessageID() {
/* 24 */     return 537133072;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 32 */       this.posUavX = data.readByte();
/* 33 */       this.posUavY = data.readByte();
/* 34 */       this.posUavZ = data.readByte();
/* 35 */       this.continueControl = (data.readByte() != 0);
/*    */     }
/* 37 */     catch (Exception e) {
/*    */       
/* 39 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 49 */       dos.writeByte(this.posUavX);
/* 50 */       dos.writeByte(this.posUavY);
/* 51 */       dos.writeByte(this.posUavZ);
/* 52 */       dos.writeByte(this.continueControl ? 1 : 0);
/*    */     }
/* 54 */     catch (IOException e) {
/*    */       
/* 56 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mchel\\uav\MCH_UavPacketStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */