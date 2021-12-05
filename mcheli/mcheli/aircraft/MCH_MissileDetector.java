/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import mcheli.MCH_PacketNotifyLock;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.weapon.MCH_EntityBaseBullet;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class MCH_MissileDetector
/*     */ {
/*     */   private MCH_EntityAircraft ac;
/*     */   private World world;
/*     */   private int alertCount;
/*     */   public static final int SEARCH_RANGE = 60;
/*     */   
/*     */   public MCH_MissileDetector(MCH_EntityAircraft aircraft, World w) {
/*  23 */     this.world = w;
/*  24 */     this.ac = aircraft;
/*  25 */     this.alertCount = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  32 */     if (!this.ac.haveFlare()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  37 */     if (this.alertCount > 0)
/*     */     {
/*  39 */       this.alertCount--;
/*     */     }
/*     */     
/*  42 */     boolean isLocked = this.ac.getEntityData().func_74767_n("Tracking");
/*  43 */     if (isLocked)
/*     */     {
/*  45 */       this.ac.getEntityData().func_74757_a("Tracking", false);
/*     */     }
/*     */     
/*  48 */     if (this.ac.getEntityData().func_74767_n("LockOn")) {
/*     */       
/*  50 */       if (this.alertCount == 0) {
/*     */         
/*  52 */         this.alertCount = 10;
/*  53 */         if (this.ac != null && this.ac.haveFlare() && !this.ac.isDestroyed())
/*     */         {
/*  55 */           for (int i = 0; i < 2; i++) {
/*     */             
/*  57 */             Entity entity = this.ac.getEntityBySeatId(i);
/*  58 */             if (entity instanceof net.minecraft.entity.player.EntityPlayerMP)
/*     */             {
/*  60 */               MCH_PacketNotifyLock.sendToPlayer((EntityPlayer)entity);
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*  65 */       this.ac.getEntityData().func_74757_a("LockOn", false);
/*     */     } 
/*     */     
/*  68 */     if (this.ac.isDestroyed()) {
/*     */       return;
/*     */     }
/*     */     
/*  72 */     Entity rider = this.ac.getRiddenByEntity();
/*  73 */     if (rider == null) rider = this.ac.getEntityBySeatId(1); 
/*  74 */     if (rider != null)
/*     */     {
/*  76 */       if (this.ac.isFlareUsing()) {
/*     */         
/*  78 */         destroyMissile();
/*     */       
/*     */       }
/*  81 */       else if (!this.ac.isUAV() && !this.world.field_72995_K) {
/*     */         
/*  83 */         if (this.alertCount == 0 && (isLocked || isLockedByMissile()))
/*     */         {
/*  85 */           this.alertCount = 20;
/*  86 */           W_WorldFunc.MOD_playSoundAtEntity((Entity)this.ac, "alert", 1.0F, 1.0F);
/*     */         }
/*     */       
/*     */       }
/*  90 */       else if (this.ac.isUAV() && this.world.field_72995_K) {
/*     */         
/*  92 */         if (this.alertCount == 0 && (isLocked || isLockedByMissile())) {
/*     */           
/*  94 */           this.alertCount = 20;
/*  95 */           if (W_Lib.isClientPlayer(rider))
/*     */           {
/*  97 */             W_McClient.MOD_playSoundFX("alert", 1.0F, 1.0F);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean destroyMissile() {
/* 106 */     List<MCH_EntityBaseBullet> list = this.world.func_72872_a(MCH_EntityBaseBullet.class, this.ac.field_70121_D.func_72314_b(60.0D, 60.0D, 60.0D));
/*     */     
/* 108 */     if (list != null)
/*     */     {
/* 110 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 112 */         MCH_EntityBaseBullet msl = list.get(i);
/* 113 */         if (msl.targetEntity != null)
/*     */         {
/* 115 */           if (this.ac.isMountedEntity(msl.targetEntity) || msl.targetEntity.equals(this.ac)) {
/*     */             
/* 117 */             msl.targetEntity = null;
/* 118 */             msl.func_70106_y();
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLockedByMissile() {
/* 128 */     List<MCH_EntityBaseBullet> list = this.world.func_72872_a(MCH_EntityBaseBullet.class, this.ac.field_70121_D.func_72314_b(60.0D, 60.0D, 60.0D));
/*     */     
/* 130 */     if (list != null)
/*     */     {
/* 132 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 134 */         MCH_EntityBaseBullet msl = list.get(i);
/* 135 */         if (msl.targetEntity != null)
/*     */         {
/* 137 */           if (this.ac.isMountedEntity(msl.targetEntity) || msl.targetEntity.equals(this.ac))
/*     */           {
/* 139 */             return true;
/*     */           }
/*     */         }
/*     */       } 
/*     */     }
/* 144 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_MissileDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */