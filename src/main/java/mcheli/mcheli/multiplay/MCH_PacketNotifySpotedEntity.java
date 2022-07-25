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
/*    */ 
/*    */ public class MCH_PacketNotifySpotedEntity
/*    */   extends MCH_Packet
/*    */ {
/* 21 */   public int count = 0;
/* 22 */   public int num = 0;
/* 23 */   public int[] entityId = null;
/*    */   
/*    */   public int getMessageID() {
/* 26 */     return 268437761;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 34 */       this.count = data.readShort();
/* 35 */       this.num = data.readShort();
/* 36 */       if (this.num > 0)
/*    */       {
/* 38 */         this.entityId = new int[this.num];
/* 39 */         for (int i = 0; i < this.num; i++)
/*    */         {
/* 41 */           this.entityId[i] = data.readInt();
/*    */         }
/*    */       }
/*    */       else
/*    */       {
/* 46 */         this.num = 0;
/*    */       }
/*    */     
/* 49 */     } catch (Exception e) {
/*    */       
/* 51 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 60 */       dos.writeShort(this.count);
/* 61 */       dos.writeShort(this.num);
/* 62 */       for (int i = 0; i < this.num; i++)
/*    */       {
/* 64 */         dos.writeInt(this.entityId[i]);
/*    */       }
/*    */     }
/* 67 */     catch (Exception e) {
/*    */       
/* 69 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(EntityPlayer player, int count, int[] entityId) {
/* 75 */     if (player == null || entityId == null || entityId.length <= 0 || count <= 0)
/* 76 */       return;  if (count > 30000)
/*    */     {
/* 78 */       count = 30000;
/*    */     }
/* 80 */     mcheli.multiplay.MCH_PacketNotifySpotedEntity pkt = new mcheli.multiplay.MCH_PacketNotifySpotedEntity();
/* 81 */     pkt.count = count;
/* 82 */     pkt.num = entityId.length;
/* 83 */     if (pkt.num > 300)
/*    */     {
/* 85 */       pkt.num = 300;
/*    */     }
/* 87 */     if (pkt.num > entityId.length)
/*    */     {
/* 89 */       pkt.num = entityId.length;
/*    */     }
/* 91 */     pkt.entityId = entityId;
/* 92 */     W_Network.sendToPlayer((W_PacketBase)pkt, player);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_PacketNotifySpotedEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */