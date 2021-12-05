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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_PacketNotifyAmmoNum
/*     */   extends MCH_Packet
/*     */ {
/*  25 */   public int entityID_Ac = -1;
/*     */   public boolean all = false;
/*  27 */   public byte weaponID = -1;
/*  28 */   public byte num = 0;
/*  29 */   public short[] ammo = new short[0];
/*  30 */   public short[] restAmmo = new short[0];
/*     */   
/*     */   public int getMessageID() {
/*  33 */     return 268439604;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readData(ByteArrayDataInput data) {
/*     */     try {
/*  41 */       this.entityID_Ac = data.readInt();
/*  42 */       this.all = (data.readByte() != 0);
/*  43 */       if (this.all) {
/*     */         
/*  45 */         this.num = data.readByte();
/*  46 */         this.ammo = new short[this.num];
/*  47 */         this.restAmmo = new short[this.num];
/*  48 */         for (int i = 0; i < this.num; i++)
/*     */         {
/*  50 */           this.ammo[i] = data.readShort();
/*  51 */           this.restAmmo[i] = data.readShort();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  56 */         this.weaponID = data.readByte();
/*  57 */         this.ammo = new short[] { data.readShort() };
/*  58 */         this.restAmmo = new short[] { data.readShort() };
/*     */       }
/*     */     
/*  61 */     } catch (Exception e) {
/*     */       
/*  63 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeData(DataOutputStream dos) {
/*     */     try {
/*  72 */       dos.writeInt(this.entityID_Ac);
/*  73 */       dos.writeByte(this.all ? 1 : 0);
/*  74 */       if (this.all) {
/*     */         
/*  76 */         dos.writeByte(this.num);
/*  77 */         for (int i = 0; i < this.num; i++)
/*     */         {
/*  79 */           dos.writeShort(this.ammo[i]);
/*  80 */           dos.writeShort(this.restAmmo[i]);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  85 */         dos.writeByte(this.weaponID);
/*  86 */         dos.writeShort(this.ammo[0]);
/*  87 */         dos.writeShort(this.restAmmo[0]);
/*     */       }
/*     */     
/*  90 */     } catch (IOException e) {
/*     */       
/*  92 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendAllAmmoNum(MCH_EntityAircraft ac, EntityPlayer target) {
/*  98 */     mcheli.aircraft.MCH_PacketNotifyAmmoNum s = new mcheli.aircraft.MCH_PacketNotifyAmmoNum();
/*     */     
/* 100 */     s.entityID_Ac = W_Entity.getEntityId((Entity)ac);
/* 101 */     s.all = true;
/* 102 */     s.num = (byte)ac.getWeaponNum();
/* 103 */     s.ammo = new short[s.num];
/* 104 */     s.restAmmo = new short[s.num];
/* 105 */     for (int i = 0; i < s.num; i++) {
/*     */       
/* 107 */       s.ammo[i] = (short)ac.getWeapon(i).getAmmoNum();
/* 108 */       s.restAmmo[i] = (short)ac.getWeapon(i).getRestAllAmmoNum();
/*     */     } 
/*     */     
/* 111 */     send(s, ac, target);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendAmmoNum(MCH_EntityAircraft ac, EntityPlayer target, int wid) {
/* 117 */     sendAmmoNum(ac, target, wid, ac.getWeapon(wid).getAmmoNum(), ac.getWeapon(wid).getRestAllAmmoNum());
/*     */   }
/*     */   
/*     */   public static void sendAmmoNum(MCH_EntityAircraft ac, EntityPlayer target, int wid, int ammo, int rest_ammo) {
/* 121 */     mcheli.aircraft.MCH_PacketNotifyAmmoNum s = new mcheli.aircraft.MCH_PacketNotifyAmmoNum();
/*     */     
/* 123 */     s.entityID_Ac = W_Entity.getEntityId((Entity)ac);
/* 124 */     s.all = false;
/* 125 */     s.weaponID = (byte)wid;
/* 126 */     s.ammo = new short[] { (short)ammo };
/* 127 */     s.restAmmo = new short[] { (short)rest_ammo };
/*     */     
/* 129 */     send(s, ac, target);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void send(mcheli.aircraft.MCH_PacketNotifyAmmoNum s, MCH_EntityAircraft ac, EntityPlayer target) {
/* 134 */     if (target == null) {
/*     */       
/* 136 */       for (int i = 0; i < ac.getSeatNum() + 1; i++)
/*     */       {
/* 138 */         Entity e = ac.getEntityBySeatId(i);
/* 139 */         if (e instanceof EntityPlayer)
/*     */         {
/* 141 */           W_Network.sendToPlayer((W_PacketBase)s, (EntityPlayer)e);
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 147 */       W_Network.sendToPlayer((W_PacketBase)s, target);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketNotifyAmmoNum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */