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
/*     */ public class MCH_PacketStatusResponse
/*     */   extends MCH_Packet
/*     */ {
/*  22 */   public int entityID_AC = -1;
/*  23 */   public byte seatNum = -1;
/*  24 */   public byte[] weaponIDs = new byte[] { -1 };
/*     */   
/*     */   public int getMessageID() {
/*  27 */     return 268439649;
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
/*  39 */         this.weaponIDs = new byte[this.seatNum];
/*  40 */         for (int i = 0; i < this.seatNum; i++)
/*     */         {
/*  42 */           this.weaponIDs[i] = data.readByte();
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
/*  58 */       if (this.seatNum > 0 && this.weaponIDs != null && this.weaponIDs.length == this.seatNum)
/*     */       {
/*  60 */         dos.writeByte(this.seatNum);
/*  61 */         for (int i = 0; i < this.seatNum; i++)
/*     */         {
/*  63 */           dos.writeByte(this.weaponIDs[i]);
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
/*     */   public static void sendStatus(MCH_EntityAircraft ac, EntityPlayer player) {
/*  79 */     mcheli.aircraft.MCH_PacketStatusResponse s = new mcheli.aircraft.MCH_PacketStatusResponse();
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
/*  90 */     this.seatNum = (byte)(ac.getSeatNum() + 1);
/*  91 */     if (this.seatNum > 0) {
/*     */       
/*  93 */       this.weaponIDs = new byte[this.seatNum];
/*  94 */       for (int i = 0; i < this.seatNum; i++)
/*     */       {
/*  96 */         this.weaponIDs[i] = (byte)ac.getWeaponIDBySeatID(i);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 101 */       this.weaponIDs = new byte[] { -1 };
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketStatusResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */