/*    */ package mcheli.mcheli.multiplay;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.multiplay.MCH_MultiplayClient;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketLargeData
/*    */   extends MCH_Packet
/*    */ {
/* 23 */   public int imageDataIndex = -1;
/* 24 */   public int imageDataSize = 0;
/* 25 */   public int imageDataTotalSize = 0;
/*    */   
/*    */   public int getMessageID() {
/* 28 */     return 536873472;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] buf;
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 36 */       this.imageDataIndex = data.readInt();
/* 37 */       this.imageDataSize = data.readInt();
/* 38 */       this.imageDataTotalSize = data.readInt();
/* 39 */       this.buf = new byte[this.imageDataSize];
/* 40 */       data.readFully(this.buf);
/*    */     }
/* 42 */     catch (Exception e) {
/*    */       
/* 44 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 55 */       MCH_MultiplayClient.readImageData(dos);
/*    */       
/*    */       return;
/* 58 */     } catch (Exception e) {
/*    */       
/* 60 */       e.printStackTrace();
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void send() {
/* 66 */     mcheli.multiplay.MCH_PacketLargeData p = new mcheli.multiplay.MCH_PacketLargeData();
/* 67 */     W_Network.sendToServer((W_PacketBase)p);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_PacketLargeData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */