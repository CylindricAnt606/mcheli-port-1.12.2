/*    */ package mcheli.mcheli.multiplay;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketIndSpotEntity
/*    */   extends MCH_Packet
/*    */ {
/* 17 */   public int targetFilter = -1;
/*    */   
/*    */   public int getMessageID() {
/* 20 */     return 536873216;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 28 */       this.targetFilter = data.readInt();
/*    */     }
/* 30 */     catch (Exception e) {
/*    */       
/* 32 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 41 */       dos.writeInt(this.targetFilter);
/*    */     }
/* 43 */     catch (Exception e) {
/*    */       
/* 45 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(EntityLivingBase spoter, int targetFilter) {
/* 51 */     mcheli.multiplay.MCH_PacketIndSpotEntity s = new mcheli.multiplay.MCH_PacketIndSpotEntity();
/* 52 */     s.targetFilter = targetFilter;
/* 53 */     W_Network.sendToServer((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_PacketIndSpotEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */