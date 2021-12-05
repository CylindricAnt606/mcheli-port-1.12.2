/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import com.google.common.io.ByteArrayDataInput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import mcheli.MCH_Packet;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_Network;
/*     */ import mcheli.wrapper.W_PacketBase;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_PacketSeatListResponse
/*     */   extends MCH_Packet
/*     */ {
/*  22 */   public int entityID_AC = -1;
/*  23 */   public byte seatNum = -1;
/*  24 */   public int[] seatEntityID = new int[] { -1 };
/*     */   
/*     */   public int getMessageID() {
/*  27 */     return 268439569;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readData(ByteArrayDataInput data) {
/*     */     try {
/*  35 */       this.entityID_AC = data.readInt();
/*  36 */       this.seatNum = data.readByte();
/*  37 */       if (this.seatNum > 0)
/*     */       {
/*  39 */         this.seatEntityID = new int[this.seatNum];
/*  40 */         for (int i = 0; i < this.seatNum; i++)
/*     */         {
/*  42 */           this.seatEntityID[i] = data.readInt();
/*     */         }
/*     */       }
/*     */     
/*  46 */     } catch (Exception e) {
/*     */       
/*  48 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeData(DataOutputStream dos) {
/*     */     try {
/*  57 */       dos.writeInt(this.entityID_AC);
/*  58 */       if (this.seatNum > 0 && this.seatEntityID != null && this.seatEntityID.length == this.seatNum)
/*     */       {
/*  60 */         dos.writeByte(this.seatNum);
/*  61 */         for (int i = 0; i < this.seatNum; i++)
/*     */         {
/*  63 */           dos.writeInt(this.seatEntityID[i]);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/*  68 */         dos.writeByte(-1);
/*     */       }
/*     */     
/*  71 */     } catch (IOException e) {
/*     */       
/*  73 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendSeatList(MCH_EntityAircraft ac, EntityPlayer player) {
/*  79 */     mcheli.aircraft.MCH_PacketSeatListResponse s = new mcheli.aircraft.MCH_PacketSeatListResponse();
/*     */     
/*  81 */     s.setParameter(ac);
/*     */     
/*  83 */     W_Network.sendToPlayer((W_PacketBase)s, player);
/*     */   }
/*     */   
/*     */   protected void setParameter(MCH_EntityAircraft ac) {
/*  87 */     if (ac == null)
/*     */       return; 
/*  89 */     this.entityID_AC = W_Entity.getEntityId((Entity)ac);
/*  90 */     this.seatNum = (byte)(ac.getSeats()).length;
/*  91 */     if (this.seatNum > 0) {
/*     */       
/*  93 */       this.seatEntityID = new int[this.seatNum];
/*  94 */       for (int i = 0; i < this.seatNum; i++)
/*     */       {
/*  96 */         this.seatEntityID[i] = W_Entity.getEntityId((Entity)ac.getSeat(i));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 101 */       this.seatEntityID = new int[] { -1 };
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketSeatListResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */