/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import mcheli.weapon.MCH_BulletModel;
/*     */ import mcheli.weapon.MCH_DefaultBulletModels;
/*     */ import mcheli.weapon.MCH_EntityBaseBullet;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_EntityATMissile
/*     */   extends MCH_EntityBaseBullet {
/*     */   public MCH_EntityATMissile(World par1World) {
/*  13 */     super(par1World);
/*  14 */     this.guidanceType = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int guidanceType;
/*     */ 
/*     */   
/*     */   public MCH_EntityATMissile(World par1World, double posX, double posY, double posZ, double targetX, double targetY, double targetZ, float yaw, float pitch, double acceleration) {
/*  22 */     super(par1World, posX, posY, posZ, targetX, targetY, targetZ, yaw, pitch, acceleration);
/*  23 */     this.guidanceType = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  29 */     super.func_70071_h_();
/*     */     
/*  31 */     if (getInfo() != null && !(getInfo()).disableSmoke && this.field_70173_aa >= (getInfo()).trajectoryParticleStartTick)
/*     */     {
/*  33 */       spawnParticle((getInfo()).trajectoryParticleName, 3, 5.0F * (getInfo()).smokeSize * 0.5F);
/*     */     }
/*     */     
/*  36 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/*  38 */       if (this.shootingEntity != null && this.targetEntity != null && !this.targetEntity.field_70128_L) {
/*     */         
/*  40 */         onUpdateMotion();
/*     */       }
/*     */       else {
/*     */         
/*  44 */         func_70106_y();
/*     */       } 
/*     */     }
/*     */     
/*  48 */     double a = (float)Math.atan2(this.field_70179_y, this.field_70159_w);
/*  49 */     this.field_70177_z = (float)(a * 180.0D / Math.PI) - 90.0F;
/*     */     
/*  51 */     double r = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*  52 */     this.field_70125_A = -((float)(Math.atan2(this.field_70181_x, r) * 180.0D / Math.PI));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdateMotion() {
/*  57 */     double x = this.targetEntity.field_70165_t - this.field_70165_t;
/*  58 */     double y = this.targetEntity.field_70163_u - this.field_70163_u;
/*  59 */     double z = this.targetEntity.field_70161_v - this.field_70161_v;
/*  60 */     double d = x * x + y * y + z * z;
/*     */     
/*  62 */     if (d > 2250000.0D || this.targetEntity.field_70128_L) {
/*     */       
/*  64 */       func_70106_y();
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  69 */     else if ((getInfo()).proximityFuseDist >= 0.1F && d < (getInfo()).proximityFuseDist) {
/*     */       
/*  71 */       MovingObjectPosition mop = new MovingObjectPosition(this.targetEntity);
/*  72 */       mop.field_72308_g = null;
/*  73 */       onImpact(mop, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/*  77 */       int rigidityTime = (getInfo()).rigidityTime;
/*     */       
/*  79 */       float af = (getCountOnUpdate() < rigidityTime + (getInfo()).trajectoryParticleStartTick) ? 0.5F : 1.0F;
/*     */       
/*  81 */       if (getCountOnUpdate() > rigidityTime)
/*     */       {
/*     */ 
/*     */         
/*  85 */         if (this.guidanceType == 1) {
/*     */           
/*  87 */           if (getCountOnUpdate() <= rigidityTime + 20)
/*     */           {
/*  89 */             guidanceToTarget(this.targetEntity.field_70165_t, this.shootingEntity.field_70163_u + 150.0D, this.targetEntity.field_70161_v, af);
/*     */           }
/*  91 */           else if (getCountOnUpdate() <= rigidityTime + 30)
/*     */           {
/*  93 */             guidanceToTarget(this.targetEntity.field_70165_t, this.shootingEntity.field_70163_u, this.targetEntity.field_70161_v, af);
/*     */           }
/*     */           else
/*     */           {
/*  97 */             if (getCountOnUpdate() == rigidityTime + 35) {
/*     */               
/*  99 */               setPower((int)(getPower() * 1.2F));
/* 100 */               if (this.explosionPower > 0) this.explosionPower++; 
/*     */             } 
/* 102 */             guidanceToTarget(this.targetEntity.field_70165_t, this.targetEntity.field_70163_u, this.targetEntity.field_70161_v, af);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 107 */           d = MathHelper.func_76133_a(d);
/* 108 */           this.field_70159_w = x * this.acceleration / d * af;
/* 109 */           this.field_70181_x = y * this.acceleration / d * af;
/* 110 */           this.field_70179_y = z * this.acceleration / d * af;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_BulletModel getDefaultBulletModel() {
/* 119 */     return MCH_DefaultBulletModels.ATMissile;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityATMissile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */