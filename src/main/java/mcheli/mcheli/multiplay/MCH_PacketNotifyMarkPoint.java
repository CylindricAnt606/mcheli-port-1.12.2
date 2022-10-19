/*    */ package mcheli.mcheli.multiplay;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketNotifyMarkPoint
/*    */   extends MCH_Packet
/*    */ {
/* 20 */   public int px = this.pz = 0;
/* 21 */   public int py = 0;
/*    */   
/*    */   public int getMessageID() {
/* 24 */     return 268437762;
/*    */   }
/*    */ 
/*    */   
/*    */   public int pz;
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 32 */       this.px = data.readInt();
/* 33 */       this.py = data.readInt();
/* 34 */       this.pz = data.readInt();
/*    */     }
/* 36 */     catch (Exception e) {
/*    */       
/* 38 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 47 */       dos.writeInt(this.px);
/* 48 */       dos.writeInt(this.py);
/* 49 */       dos.writeInt(this.pz);
/*    */     }
/* 51 */     catch (Exception e) {
/*    */       
/* 53 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(EntityPlayer player, int x, int y, int z) {
/* 59 */     mcheli.multiplay.MCH_PacketNotifyMarkPoint pkt = new mcheli.multiplay.MCH_PacketNotifyMarkPoint();
/* 60 */     pkt.px = x;
/* 61 */     pkt.py = y;
/* 62 */     pkt.pz = z;
/* 63 */     W_Network.sendToPlayer((W_PacketBase)pkt, player);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_PacketNotifyMarkPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */