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
/*    */ public class MCH_PacketStatusRequest
/*    */   extends MCH_Packet
/*    */ {
/* 18 */   public int entityID_AC = -1;
/*    */   
/*    */   public int getMessageID() {
/* 21 */     return 536875104;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 29 */       this.entityID_AC = data.readInt();
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
/* 42 */       dos.writeInt(this.entityID_AC);
/*    */     }
/* 44 */     catch (IOException e) {
/*    */       
/* 46 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void requestStatus(MCH_EntityAircraft ac) {
/* 52 */     if (ac.field_70170_p.field_72995_K) {
/*    */       
/* 54 */       mcheli.aircraft.MCH_PacketStatusRequest s = new mcheli.aircraft.MCH_PacketStatusRequest();
/* 55 */       s.entityID_AC = W_Entity.getEntityId((Entity)ac);
/* 56 */       W_Network.sendToServer((W_PacketBase)s);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketStatusRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */