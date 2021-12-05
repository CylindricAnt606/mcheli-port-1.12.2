/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Config;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketNotifyServerSettings
/*    */   extends MCH_Packet
/*    */ {
/*    */   public boolean enableCamDistChange = true;
/*    */   public boolean enableEntityMarker = true;
/*    */   public boolean enablePVP = true;
/* 25 */   public double stingerLockRange = 120.0D;
/*    */   public boolean enableDebugBoundingBox = true;
/*    */   
/*    */   public int getMessageID() {
/* 29 */     return 268437568;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 37 */       byte b = data.readByte();
/* 38 */       this.enableCamDistChange = getBit(b, 0);
/* 39 */       this.enableEntityMarker = getBit(b, 1);
/* 40 */       this.enablePVP = getBit(b, 2);
/* 41 */       this.stingerLockRange = data.readFloat();
/* 42 */       this.enableDebugBoundingBox = getBit(b, 3);
/*    */     }
/* 44 */     catch (Exception e) {
/*    */       
/* 46 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 55 */       byte b = 0;
/* 56 */       b = setBit(b, 0, this.enableCamDistChange);
/* 57 */       b = setBit(b, 1, this.enableEntityMarker);
/* 58 */       b = setBit(b, 2, this.enablePVP);
/* 59 */       b = setBit(b, 3, this.enableDebugBoundingBox);
/* 60 */       dos.writeByte(b);
/* 61 */       dos.writeFloat((float)this.stingerLockRange);
/*    */     }
/* 63 */     catch (IOException e) {
/*    */       
/* 65 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(EntityPlayerMP player) {
/* 71 */     mcheli.MCH_PacketNotifyServerSettings s = new mcheli.MCH_PacketNotifyServerSettings();
/* 72 */     s.enableCamDistChange = !MCH_Config.DisableCameraDistChange.prmBool;
/* 73 */     s.enableEntityMarker = MCH_Config.DisplayEntityMarker.prmBool;
/* 74 */     s.enablePVP = MinecraftServer.func_71276_C().func_71219_W();
/* 75 */     s.stingerLockRange = MCH_Config.StingerLockRange.prmDouble;
/* 76 */     s.enableDebugBoundingBox = MCH_Config.EnableDebugBoundingBox.prmBool;
/* 77 */     if (player != null) {
/*    */       
/* 79 */       W_Network.sendToPlayer((W_PacketBase)s, (EntityPlayer)player);
/*    */     }
/*    */     else {
/*    */       
/* 83 */       W_Network.sendToAllPlayers((W_PacketBase)s);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void sendAll() {
/* 88 */     send(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_PacketNotifyServerSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */