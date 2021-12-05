/*    */ package mcheli.mcheli.plane;
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
/*    */ public class MCP_PlanePacketPlayerControl
/*    */   extends MCH_PacketPlayerControlBase
/*    */ {
/* 17 */   public byte switchVtol = -1;
/*    */   
/*    */   public int getMessageID() {
/* 20 */     return 536903696;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/* 26 */     super.readData(data);
/*    */     
/*    */     try {
/* 29 */       this.switchVtol = data.readByte();
/*    */     }
/* 31 */     catch (Exception e) {
/*    */       
/* 33 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/* 41 */     super.writeData(dos);
/*    */     
/*    */     try {
/* 44 */       dos.writeByte(this.switchVtol);
/*    */     }
/* 46 */     catch (IOException e) {
/*    */       
/* 48 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\plane\MCP_PlanePacketPlayerControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */