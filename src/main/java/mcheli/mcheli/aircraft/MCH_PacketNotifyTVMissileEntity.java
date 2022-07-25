/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketNotifyTVMissileEntity
/*    */   extends MCH_Packet
/*    */ {
/* 16 */   public int entityID_Ac = -1;
/* 17 */   public int entityID_TVMissile = -1;
/*    */   
/*    */   public int getMessageID() {
/* 20 */     return 268439600;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 28 */       this.entityID_Ac = data.readInt();
/* 29 */       this.entityID_TVMissile = data.readInt();
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
/*    */     try {
/* 42 */       dos.writeInt(this.entityID_Ac);
/* 43 */       dos.writeInt(this.entityID_TVMissile);
/*    */     }
/* 45 */     catch (IOException e) {
/*    */       
/* 47 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(int heliEntityID, int tvMissileEntityID) {
/* 53 */     mcheli.aircraft.MCH_PacketNotifyTVMissileEntity s = new mcheli.aircraft.MCH_PacketNotifyTVMissileEntity();
/*    */     
/* 55 */     s.entityID_Ac = heliEntityID;
/* 56 */     s.entityID_TVMissile = tvMissileEntityID;
/*    */     
/* 58 */     W_Network.sendToAllPlayers((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketNotifyTVMissileEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */