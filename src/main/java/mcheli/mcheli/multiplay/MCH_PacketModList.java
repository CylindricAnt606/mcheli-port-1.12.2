/*     */ package mcheli.mcheli.multiplay;
/*     */ 
/*     */ import com.google.common.io.ByteArrayDataInput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Packet;
/*     */ import mcheli.wrapper.W_Network;
/*     */ import mcheli.wrapper.W_PacketBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_PacketModList
/*     */   extends MCH_Packet
/*     */ {
/*     */   public boolean firstData = false;
/*  27 */   public int id = 0;
/*  28 */   public int num = 0;
/*  29 */   public List<String> list = new ArrayList<String>();
/*     */   
/*     */   public int getMessageID() {
/*  32 */     return 536873473;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readData(ByteArrayDataInput data) {
/*     */     try {
/*  40 */       this.firstData = (data.readByte() == 1);
/*  41 */       this.id = data.readInt();
/*  42 */       this.num = data.readInt();
/*  43 */       for (int i = 0; i < this.num; i++)
/*     */       {
/*  45 */         this.list.add(data.readUTF());
/*     */       }
/*     */     }
/*  48 */     catch (Exception e) {
/*     */       
/*  50 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeData(DataOutputStream dos) {
/*     */     try {
/*  59 */       dos.writeByte(this.firstData ? 1 : 0);
/*  60 */       dos.writeInt(this.id);
/*  61 */       dos.writeInt(this.num);
/*  62 */       for (String s : this.list)
/*     */       {
/*  64 */         dos.writeUTF(s);
/*     */       }
/*     */     }
/*  67 */     catch (Exception e) {
/*     */       
/*  69 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void send(EntityPlayer player, mcheli.multiplay.MCH_PacketModList p) {
/*  75 */     W_Network.sendToPlayer((W_PacketBase)p, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void send(List<String> list, int id) {
/*  80 */     mcheli.multiplay.MCH_PacketModList p = null;
/*  81 */     int size = 0;
/*  82 */     boolean isFirst = true;
/*  83 */     for (String s : list) {
/*     */       
/*  85 */       if (p == null) {
/*     */         
/*  87 */         p = new mcheli.multiplay.MCH_PacketModList();
/*  88 */         p.id = id;
/*  89 */         p.firstData = isFirst;
/*  90 */         isFirst = false;
/*     */       } 
/*  92 */       p.list.add(s);
/*  93 */       size += s.length() + 2;
/*  94 */       if (size > 1024) {
/*     */         
/*  96 */         p.num = p.list.size();
/*  97 */         W_Network.sendToServer((W_PacketBase)p);
/*  98 */         p = null;
/*  99 */         size = 0;
/*     */       } 
/*     */     } 
/* 102 */     if (p != null) {
/*     */       
/* 104 */       p.num = p.list.size();
/* 105 */       W_Network.sendToServer((W_PacketBase)p);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_PacketModList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */