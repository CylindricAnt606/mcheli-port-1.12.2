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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketNotifyInfoReloaded
/*    */   extends MCH_Packet
/*    */ {
/* 19 */   public int type = -1;
/*    */   
/*    */   public int getMessageID() {
/* 22 */     return 536875063;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 30 */       this.type = data.readInt();
/*    */     }
/* 32 */     catch (Exception e) {
/*    */       
/* 34 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 43 */       dos.writeInt(this.type);
/*    */     }
/* 45 */     catch (IOException e) {
/*    */       
/* 47 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void sendRealodAc() {
/* 53 */     mcheli.aircraft.MCH_PacketNotifyInfoReloaded s = new mcheli.aircraft.MCH_PacketNotifyInfoReloaded();
/* 54 */     s.type = 0;
/* 55 */     W_Network.sendToServer((W_PacketBase)s);
/*    */   }
/*    */   
/*    */   public static void sendRealodAllWeapon() {
/* 59 */     mcheli.aircraft.MCH_PacketNotifyInfoReloaded s = new mcheli.aircraft.MCH_PacketNotifyInfoReloaded();
/* 60 */     s.type = 1;
/* 61 */     W_Network.sendToServer((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketNotifyInfoReloaded.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */