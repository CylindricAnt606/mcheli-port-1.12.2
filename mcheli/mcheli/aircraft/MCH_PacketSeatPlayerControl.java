/*    */ package mcheli.mcheli.aircraft;
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
/*    */ public class MCH_PacketSeatPlayerControl
/*    */   extends MCH_Packet
/*    */ {
/*    */   public boolean isUnmount = false;
/* 18 */   public byte switchSeat = 0;
/*    */   
/*    */   public int getMessageID() {
/* 21 */     return 536875040;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean parachuting;
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 29 */       byte bf = data.readByte();
/* 30 */       this.isUnmount = ((bf >> 3 & 0x1) != 0);
/* 31 */       this.switchSeat = (byte)(bf >> 1 & 0x3);
/* 32 */       this.parachuting = ((bf >> 0 & 0x1) != 0);
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
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 46 */       byte bf = (byte)((this.isUnmount ? 8 : 0) | this.switchSeat << 1 | (this.parachuting ? 1 : 0));
/*    */ 
/*    */ 
/*    */       
/* 50 */       dos.writeByte(bf);
/*    */     }
/* 52 */     catch (IOException e) {
/*    */       
/* 54 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketSeatPlayerControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */