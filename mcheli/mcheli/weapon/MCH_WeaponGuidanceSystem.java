/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_PacketNotifyLock;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.weapon.MCH_IEntityLockChecker;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import mcheli.wrapper.W_MovingObjectPosition;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class MCH_WeaponGuidanceSystem
/*     */ {
/*     */   protected World worldObj;
/*     */   public Entity lastLockEntity;
/*     */   private Entity targetEntity;
/*     */   private int lockCount;
/*     */   private int lockSoundCount;
/*     */   private int continueLockCount;
/*     */   private int lockCountMax;
/*     */   private int prevLockCount;
/*     */   public boolean canLockInWater;
/*     */   public boolean canLockOnGround;
/*     */   public boolean canLockInAir;
/*     */   public boolean ridableOnly;
/*     */   public double lockRange;
/*     */   public int lockAngle;
/*     */   public MCH_IEntityLockChecker checker;
/*     */   
/*     */   public MCH_WeaponGuidanceSystem() {
/*  38 */     this(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_WeaponGuidanceSystem(World w) {
/*  43 */     this.worldObj = w;
/*  44 */     this.targetEntity = null;
/*  45 */     this.lastLockEntity = null;
/*  46 */     this.lockCount = 0;
/*  47 */     this.continueLockCount = 0;
/*  48 */     this.lockCountMax = 1;
/*  49 */     this.prevLockCount = 0;
/*  50 */     this.canLockInWater = false;
/*  51 */     this.canLockOnGround = false;
/*  52 */     this.canLockInAir = false;
/*  53 */     this.ridableOnly = false;
/*  54 */     this.lockRange = 50.0D;
/*  55 */     this.lockAngle = 10;
/*  56 */     this.checker = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWorld(World w) {
/*  61 */     this.worldObj = w;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLockCountMax(int i) {
/*  66 */     this.lockCountMax = (i > 0) ? i : 1;
/*     */   }
/*     */   
/*     */   public int getLockCountMax() {
/*  70 */     float stealth = getEntityStealth(this.targetEntity);
/*     */     
/*  72 */     return (int)(this.lockCountMax + this.lockCountMax * stealth);
/*     */   }
/*     */   
/*     */   public int getLockCount() {
/*  76 */     return this.lockCount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLockingEntity(Entity entity) {
/*  82 */     return (getLockCount() > 0 && this.targetEntity != null && !this.targetEntity.field_70128_L && W_Entity.isEqual(entity, this.targetEntity));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getLockingEntity() {
/*  89 */     return (getLockCount() > 0 && this.targetEntity != null && !this.targetEntity.field_70128_L) ? this.targetEntity : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getTargetEntity() {
/*  94 */     return this.targetEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLockComplete() {
/*  99 */     return (getLockCount() == getLockCountMax() && this.lastLockEntity != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 104 */     if (this.worldObj != null && this.worldObj.field_72995_K)
/*     */     {
/* 106 */       if (this.lockCount != this.prevLockCount) {
/*     */         
/* 108 */         this.prevLockCount = this.lockCount;
/*     */       }
/*     */       else {
/*     */         
/* 112 */         this.lockCount = this.prevLockCount = 0;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEntityOnGround(Entity entity) {
/* 119 */     if (entity != null && !entity.field_70128_L) {
/*     */       
/* 121 */       if (entity.field_70122_E) return true; 
/* 122 */       for (int i = 0; i < 12; i++) {
/*     */         
/* 124 */         int x = (int)(entity.field_70165_t + 0.5D);
/* 125 */         int y = (int)(entity.field_70163_u + 0.5D) - i;
/* 126 */         int z = (int)(entity.field_70161_v + 0.5D);
/* 127 */         int blockId = W_WorldFunc.getBlockId(entity.field_70170_p, x, y, z);
/* 128 */         if (blockId != 0 && !W_WorldFunc.isBlockWater(entity.field_70170_p, x, y, z)) return true; 
/*     */       } 
/*     */     } 
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean lock(Entity user) {
/* 137 */     return lock(user, true);
/*     */   }
/*     */   
/*     */   public boolean lock(Entity user, boolean isLockContinue) {
/* 141 */     if (!this.worldObj.field_72995_K) return false;
/*     */     
/* 143 */     boolean result = false;
/* 144 */     if (this.lockCount == 0) {
/*     */       
/* 146 */       List<Entity> list = this.worldObj.func_72839_b(user, user.field_70121_D.func_72314_b(this.lockRange, this.lockRange, this.lockRange));
/*     */ 
/*     */ 
/*     */       
/* 150 */       Entity tgtEnt = null;
/* 151 */       double dist = this.lockRange * this.lockRange * 2.0D;
/* 152 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 154 */         Entity entity = list.get(i);
/*     */         
/* 156 */         if (canLockEntity(entity)) {
/*     */           
/* 158 */           double dx = entity.field_70165_t - user.field_70165_t;
/* 159 */           double dy = entity.field_70163_u - user.field_70163_u;
/* 160 */           double dz = entity.field_70161_v - user.field_70161_v;
/* 161 */           double d = dx * dx + dy * dy + dz * dz;
/*     */           
/* 163 */           Entity entityLocker = getLockEntity(user);
/*     */           
/* 165 */           float stealth = 1.0F - getEntityStealth(entity);
/* 166 */           double range = this.lockRange * stealth;
/* 167 */           float angle = this.lockAngle * (stealth / 2.0F + 0.5F);
/*     */           
/* 169 */           if (d < range * range && d < dist && inLockRange(entityLocker, user.field_70177_z, user.field_70125_A, entity, angle)) {
/*     */ 
/*     */             
/* 172 */             Vec3 v1 = W_WorldFunc.getWorldVec3(this.worldObj, entityLocker.field_70165_t, entityLocker.field_70163_u, entityLocker.field_70161_v);
/* 173 */             Vec3 v2 = W_WorldFunc.getWorldVec3(this.worldObj, entity.field_70165_t, entity.field_70163_u + (entity.field_70131_O / 2.0F), entity.field_70161_v);
/* 174 */             MovingObjectPosition m = W_WorldFunc.clip(this.worldObj, v1, v2, false, true, false);
/* 175 */             if (m == null || W_MovingObjectPosition.isHitTypeEntity(m)) {
/*     */               
/* 177 */               d = dist;
/* 178 */               tgtEnt = entity;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 183 */       this.targetEntity = tgtEnt;
/* 184 */       if (tgtEnt != null)
/*     */       {
/* 186 */         this.lockCount++;
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 192 */     else if (this.targetEntity != null && !this.targetEntity.field_70128_L) {
/*     */       
/* 194 */       boolean canLock = true;
/* 195 */       if (!this.canLockInWater && this.targetEntity.func_70090_H()) canLock = false; 
/* 196 */       boolean ong = isEntityOnGround(this.targetEntity);
/* 197 */       if (!this.canLockOnGround && ong) canLock = false; 
/* 198 */       if (!this.canLockInAir && !ong) canLock = false;
/*     */       
/* 200 */       if (canLock)
/*     */       {
/* 202 */         double dx = this.targetEntity.field_70165_t - user.field_70165_t;
/* 203 */         double dy = this.targetEntity.field_70163_u - user.field_70163_u;
/* 204 */         double dz = this.targetEntity.field_70161_v - user.field_70161_v;
/*     */         
/* 206 */         float stealth = 1.0F - getEntityStealth(this.targetEntity);
/* 207 */         double range = this.lockRange * stealth;
/*     */ 
/*     */         
/* 210 */         if (dx * dx + dy * dy + dz * dz < range * range) {
/*     */           
/* 212 */           if (this.worldObj.field_72995_K && this.lockSoundCount == 1)
/*     */           {
/* 214 */             MCH_PacketNotifyLock.send(getTargetEntity());
/*     */           }
/* 216 */           this.lockSoundCount = (this.lockSoundCount + 1) % 15;
/*     */ 
/*     */           
/* 219 */           Entity entityLocker = getLockEntity(user);
/* 220 */           if (inLockRange(entityLocker, user.field_70177_z, user.field_70125_A, this.targetEntity, this.lockAngle)) {
/*     */             
/* 222 */             if (this.lockCount < getLockCountMax())
/*     */             {
/* 224 */               this.lockCount++;
/*     */             
/*     */             }
/*     */           }
/* 228 */           else if (this.continueLockCount > 0) {
/*     */             
/* 230 */             this.continueLockCount--;
/* 231 */             if (this.continueLockCount <= 0 && this.lockCount > 0)
/*     */             {
/* 233 */               this.lockCount--;
/*     */             }
/*     */           }
/*     */           else {
/*     */             
/* 238 */             this.continueLockCount = 0;
/* 239 */             this.lockCount--;
/*     */           } 
/*     */           
/* 242 */           if (this.lockCount >= getLockCountMax())
/*     */           {
/* 244 */             if (this.continueLockCount <= 0) {
/*     */               
/* 246 */               this.continueLockCount = getLockCountMax() / 3;
/* 247 */               if (this.continueLockCount > 20) this.continueLockCount = 20;
/*     */             
/*     */             } 
/* 250 */             result = true;
/* 251 */             this.lastLockEntity = this.targetEntity;
/* 252 */             if (isLockContinue)
/*     */             {
/*     */               
/* 255 */               this.prevLockCount = this.lockCount - 1;
/*     */             }
/*     */             else
/*     */             {
/* 259 */               clearLock();
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 266 */           clearLock();
/*     */         }
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 272 */         clearLock();
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 278 */       clearLock();
/*     */     } 
/*     */ 
/*     */     
/* 282 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getEntityStealth(Entity entity) {
/* 287 */     if (entity instanceof MCH_EntityAircraft)
/*     */     {
/* 289 */       return ((MCH_EntityAircraft)entity).getStealth();
/*     */     }
/* 291 */     if (entity != null && entity.field_70154_o instanceof MCH_EntityAircraft)
/*     */     {
/* 293 */       return ((MCH_EntityAircraft)entity.field_70154_o).getStealth();
/*     */     }
/*     */     
/* 296 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearLock() {
/* 301 */     this.targetEntity = null;
/* 302 */     this.lockCount = 0;
/* 303 */     this.continueLockCount = 0;
/* 304 */     this.lockSoundCount = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getLockEntity(Entity entity) {
/* 309 */     if (entity.field_70154_o instanceof MCH_EntityUavStation) {
/*     */       
/* 311 */       MCH_EntityUavStation us = (MCH_EntityUavStation)entity.field_70154_o;
/* 312 */       if (us.getControlAircract() != null)
/*     */       {
/* 314 */         return (Entity)us.getControlAircract();
/*     */       }
/*     */     } 
/* 317 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canLockEntity(Entity entity) {
/* 322 */     if (this.ridableOnly && entity instanceof net.minecraft.entity.player.EntityPlayer)
/*     */     {
/* 324 */       if (entity.field_70154_o == null) return false;
/*     */     
/*     */     }
/* 327 */     String className = entity.getClass().getName();
/*     */ 
/*     */     
/* 330 */     if (className.indexOf("EntityCamera") >= 0) return false;
/*     */ 
/*     */     
/* 333 */     if (!W_Lib.isEntityLivingBase(entity) && !(entity instanceof MCH_EntityAircraft))
/*     */     {
/* 335 */       if (className.indexOf("EntityVehicle") < 0 && className.indexOf("EntityPlane") < 0 && className.indexOf("EntityMecha") < 0 && className.indexOf("EntityAAGun") < 0)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 340 */         return false;
/*     */       }
/*     */     }
/* 343 */     if (!this.canLockInWater && entity.func_70090_H()) return false; 
/* 344 */     if (this.checker != null && !this.checker.canLockEntity(entity)) return false; 
/* 345 */     boolean ong = isEntityOnGround(entity);
/* 346 */     if (!this.canLockOnGround && ong) return false; 
/* 347 */     if (!this.canLockInAir && !ong) return false; 
/* 348 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean inLockRange(Entity entity, float rotationYaw, float rotationPitch, Entity target, float lockAng) {
/* 354 */     double dx = target.field_70165_t - entity.field_70165_t;
/* 355 */     double dy = target.field_70163_u + (target.field_70131_O / 2.0F) - entity.field_70163_u;
/* 356 */     double dz = target.field_70161_v - entity.field_70161_v;
/*     */     
/* 358 */     float entityYaw = (float)MCH_Lib.getRotate360(rotationYaw);
/* 359 */     float targetYaw = (float)MCH_Lib.getRotate360(Math.atan2(dz, dx) * 180.0D / Math.PI);
/* 360 */     float diffYaw = (float)MCH_Lib.getRotate360((targetYaw - entityYaw - 90.0F));
/*     */     
/* 362 */     double dxz = Math.sqrt(dx * dx + dz * dz);
/* 363 */     float targetPitch = -((float)(Math.atan2(dy, dxz) * 180.0D / Math.PI));
/* 364 */     float diffPitch = targetPitch - rotationPitch;
/*     */     
/* 366 */     return ((diffYaw < lockAng || diffYaw > 360.0F - lockAng) && Math.abs(diffPitch) < lockAng);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponGuidanceSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */