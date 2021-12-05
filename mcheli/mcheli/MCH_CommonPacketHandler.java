/*     */ package mcheli.mcheli;
/*     */ import com.google.common.io.ByteArrayDataInput;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Explosion;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.MCH_PacketEffectExplosion;
/*     */ import mcheli.MCH_PacketIndOpenScreen;
/*     */ import mcheli.MCH_PacketNotifyLock;
/*     */ import mcheli.MCH_PacketNotifyServerSettings;
/*     */ import mcheli.MCH_ServerSettings;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.lweapon.MCH_ClientLightWeaponTickHandler;
/*     */ import mcheli.wrapper.W_Reflection;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ public class MCH_CommonPacketHandler {
/*     */   public static void onPacketEffectExplosion(EntityPlayer player, ByteArrayDataInput data) {
/*  20 */     if (!player.field_70170_p.field_72995_K)
/*     */       return; 
/*  22 */     MCH_PacketEffectExplosion pkt = new MCH_PacketEffectExplosion();
/*  23 */     pkt.readData(data);
/*     */     
/*  25 */     Entity exploder = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     if (player.func_70092_e(pkt.prm.posX, pkt.prm.posY, pkt.prm.posZ) <= 40000.0D)
/*     */     {
/*  35 */       if (!pkt.prm.inWater) {
/*     */         
/*  37 */         if (!MCH_Config.DefaultExplosionParticle.prmBool)
/*     */         {
/*  39 */           MCH_Explosion.effectExplosion(player.field_70170_p, exploder, pkt.prm.posX, pkt.prm.posY, pkt.prm.posZ, pkt.prm.size, true);
/*     */         
/*     */         }
/*     */         else
/*     */         {
/*  44 */           MCH_Explosion.DEF_effectExplosion(player.field_70170_p, exploder, pkt.prm.posX, pkt.prm.posY, pkt.prm.posZ, pkt.prm.size, true);
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  50 */         MCH_Explosion.effectExplosionInWater(player.field_70170_p, exploder, pkt.prm.posX, pkt.prm.posY, pkt.prm.posZ, pkt.prm.size, true);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketIndOpenScreen(EntityPlayer player, ByteArrayDataInput data) {
/*  60 */     if (player.field_70170_p.field_72995_K)
/*     */       return; 
/*  62 */     MCH_PacketIndOpenScreen pkt = new MCH_PacketIndOpenScreen();
/*  63 */     pkt.readData(data);
/*     */     
/*  65 */     if (pkt.guiID == 3) {
/*     */       
/*  67 */       MCH_EntityAircraft ac = MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)player);
/*  68 */       if (ac != null)
/*     */       {
/*  70 */         ac.openInventory(player);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  75 */       player.openGui(MCH_MOD.instance, pkt.guiID, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketNotifyServerSettings(EntityPlayer player, ByteArrayDataInput data) {
/*  84 */     if (!player.field_70170_p.field_72995_K)
/*     */       return; 
/*  86 */     MCH_Lib.DbgLog(false, "onPacketNotifyServerSettings:" + player, new Object[0]);
/*     */     
/*  88 */     MCH_PacketNotifyServerSettings pkt = new MCH_PacketNotifyServerSettings();
/*  89 */     pkt.readData(data);
/*     */     
/*  91 */     if (!pkt.enableCamDistChange)
/*     */     {
/*  93 */       W_Reflection.setThirdPersonDistance(4.0F);
/*     */     }
/*     */     
/*  96 */     MCH_ServerSettings.enableCamDistChange = pkt.enableCamDistChange;
/*  97 */     MCH_ServerSettings.enableEntityMarker = pkt.enableEntityMarker;
/*  98 */     MCH_ServerSettings.enablePVP = pkt.enablePVP;
/*  99 */     MCH_ServerSettings.stingerLockRange = pkt.stingerLockRange;
/* 100 */     MCH_ServerSettings.enableDebugBoundingBox = pkt.enableDebugBoundingBox;
/* 101 */     MCH_ClientLightWeaponTickHandler.lockRange = MCH_ServerSettings.stingerLockRange;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketNotifyLock(EntityPlayer player, ByteArrayDataInput data) {
/* 110 */     MCH_PacketNotifyLock pkt = new MCH_PacketNotifyLock();
/* 111 */     pkt.readData(data);
/*     */ 
/*     */     
/* 114 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/* 116 */       if (pkt.entityID >= 0) {
/*     */         
/* 118 */         Entity target = player.field_70170_p.func_73045_a(pkt.entityID);
/* 119 */         if (target != null)
/*     */         {
/* 121 */           MCH_EntityAircraft ac = null;
/* 122 */           if (target instanceof MCH_EntityAircraft) {
/*     */             
/* 124 */             ac = (MCH_EntityAircraft)target;
/*     */           }
/* 126 */           else if (target instanceof MCH_EntitySeat) {
/*     */             
/* 128 */             ac = ((MCH_EntitySeat)target).getParent();
/*     */           }
/*     */           else {
/*     */             
/* 132 */             ac = MCH_EntityAircraft.getAircraft_RiddenOrControl(target);
/*     */           } 
/* 134 */           if (ac != null && ac.haveFlare() && !ac.isDestroyed())
/*     */           {
/* 136 */             for (int i = 0; i < 2; i++)
/*     */             {
/* 138 */               Entity entity = ac.getEntityBySeatId(i);
/* 139 */               if (entity instanceof net.minecraft.entity.player.EntityPlayerMP)
/*     */               {
/* 141 */                 MCH_PacketNotifyLock.sendToPlayer((EntityPlayer)entity);
/*     */               }
/*     */             }
/*     */           
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 151 */       MCH_MOD.proxy.clientLocked();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_CommonPacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */