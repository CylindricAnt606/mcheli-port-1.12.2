/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.wrapper.W_Entity;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketSeatListRequest
/*    */   extends MCH_Packet
/*    */ {
/* 19 */   public int entityID_AC = -1;
/*    */   
/*    */   public int getMessageID() {
/* 22 */     return 536875024;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 30 */       this.entityID_AC = data.readInt();
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
/* 43 */       dos.writeInt(this.entityID_AC);
/*    */     }
/* 45 */     catch (IOException e) {
/*    */       
/* 47 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void requestSeatList(MCH_EntityAircraft ac) {
/* 53 */     mcheli.aircraft.MCH_PacketSeatListRequest s = new mcheli.aircraft.MCH_PacketSeatListRequest();
/*    */     
/* 55 */     s.entityID_AC = W_Entity.getEntityId((Entity)ac);
/*    */     
/* 57 */     W_Network.sendToServer((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketSeatListRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */