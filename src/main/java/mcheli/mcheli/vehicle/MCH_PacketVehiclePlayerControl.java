/*    */ package mcheli.mcheli.vehicle;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.aircraft.MCH_PacketPlayerControlBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketVehiclePlayerControl
/*    */   extends MCH_PacketPlayerControlBase
/*    */ {
/* 18 */   public byte switchFold = -1;
/* 19 */   public int unhitchChainId = -1;
/*    */   
/*    */   public int getMessageID() {
/* 22 */     return 537002000;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/* 28 */     super.readData(data);
/*    */     
/*    */     try {
/* 31 */       this.switchFold = data.readByte();
/* 32 */       this.unhitchChainId = data.readInt();
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
/* 44 */     super.writeData(dos);
/*    */     
/*    */     try {
/* 47 */       dos.writeByte(this.switchFold);
/* 48 */       dos.writeInt(this.unhitchChainId);
/*    */     }
/* 50 */     catch (IOException e) {
/*    */       
/* 52 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\vehicle\MCH_PacketVehiclePlayerControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */