/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Config;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.wrapper.W_EntityRenderer;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketNotifyClientSetting
/*    */   extends MCH_Packet
/*    */ {
/*    */   public boolean dismountAll = true;
/*    */   public boolean heliAutoThrottleDown;
/*    */   public boolean planeAutoThrottleDown;
/*    */   public boolean tankAutoThrottleDown;
/*    */   public boolean shaderSupport = false;
/*    */   
/*    */   public int getMessageID() {
/* 27 */     return 536875072;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput di) {
/*    */     try {
/* 35 */       byte data = 0;
/* 36 */       data = di.readByte();
/* 37 */       this.dismountAll = getBit(data, 0);
/* 38 */       this.heliAutoThrottleDown = getBit(data, 1);
/* 39 */       this.planeAutoThrottleDown = getBit(data, 2);
/* 40 */       this.tankAutoThrottleDown = getBit(data, 3);
/* 41 */       this.shaderSupport = getBit(data, 4);
/*    */     }
/* 43 */     catch (Exception e) {
/*    */       
/* 45 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 54 */       byte data = 0;
/* 55 */       data = setBit(data, 0, this.dismountAll);
/* 56 */       data = setBit(data, 1, this.heliAutoThrottleDown);
/* 57 */       data = setBit(data, 2, this.planeAutoThrottleDown);
/* 58 */       data = setBit(data, 3, this.tankAutoThrottleDown);
/* 59 */       data = setBit(data, 4, this.shaderSupport);
/* 60 */       dos.writeByte(data);
/*    */     }
/* 62 */     catch (IOException e) {
/*    */       
/* 64 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send() {
/* 70 */     mcheli.aircraft.MCH_PacketNotifyClientSetting s = new mcheli.aircraft.MCH_PacketNotifyClientSetting();
/*    */     
/* 72 */     s.dismountAll = MCH_Config.DismountAll.prmBool;
/* 73 */     s.heliAutoThrottleDown = MCH_Config.AutoThrottleDownHeli.prmBool;
/* 74 */     s.planeAutoThrottleDown = MCH_Config.AutoThrottleDownPlane.prmBool;
/* 75 */     s.tankAutoThrottleDown = MCH_Config.AutoThrottleDownTank.prmBool;
/* 76 */     s.shaderSupport = W_EntityRenderer.isShaderSupport();
/*    */     
/* 78 */     W_Network.sendToServer((W_PacketBase)s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketNotifyClientSetting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */