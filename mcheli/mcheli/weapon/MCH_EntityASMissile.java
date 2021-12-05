/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import mcheli.weapon.MCH_BulletModel;
/*     */ import mcheli.weapon.MCH_DefaultBulletModels;
/*     */ import mcheli.weapon.MCH_EntityBaseBullet;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_EntityASMissile extends MCH_EntityBaseBullet {
/*     */   public double targetPosX;
/*     */   
/*     */   public MCH_EntityASMissile(World par1World) {
/*  17 */     super(par1World);
/*  18 */     this.targetPosX = 0.0D;
/*  19 */     this.targetPosY = 0.0D;
/*  20 */     this.targetPosZ = 0.0D;
/*     */   }
/*     */   public double targetPosY; public double targetPosZ;
/*     */   
/*     */   public float getGravity() {
/*  25 */     if (getBomblet() == 1)
/*     */     {
/*  27 */       return -0.03F;
/*     */     }
/*  29 */     return super.getGravity();
/*     */   }
/*     */   
/*     */   public float getGravityInWater() {
/*  33 */     if (getBomblet() == 1)
/*     */     {
/*  35 */       return -0.03F;
/*     */     }
/*  37 */     return super.getGravityInWater();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  43 */     super.func_70071_h_();
/*     */     
/*  45 */     if (getInfo() != null && !(getInfo()).disableSmoke && getBomblet() == 0)
/*     */     {
/*  47 */       spawnParticle((getInfo()).trajectoryParticleName, 3, 10.0F * (getInfo()).smokeSize * 0.5F);
/*     */     }
/*     */     
/*  50 */     if (getInfo() != null && !this.field_70170_p.field_72995_K && this.isBomblet != 1) {
/*     */ 
/*     */ 
/*     */       
/*  54 */       Block block = W_WorldFunc.getBlock(this.field_70170_p, (int)this.targetPosX, (int)this.targetPosY, (int)this.targetPosZ);
/*     */ 
/*     */ 
/*     */       
/*  58 */       if (block != null && block.func_149703_v()) {
/*     */         
/*  60 */         double dist = func_70011_f(this.targetPosX, this.targetPosY, this.targetPosZ);
/*  61 */         if (dist < (getInfo()).proximityFuseDist) {
/*     */           
/*  63 */           if ((getInfo()).bomblet > 0) {
/*     */             
/*  65 */             for (int i = 0; i < (getInfo()).bomblet; i++)
/*     */             {
/*  67 */               sprinkleBomblet();
/*     */             }
/*     */           }
/*     */           else {
/*     */             
/*  72 */             MovingObjectPosition mop = new MovingObjectPosition((Entity)this);
/*  73 */             onImpact(mop, 1.0F);
/*     */           } 
/*  75 */           func_70106_y();
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*  80 */         else if (getGravity() == 0.0D) {
/*     */           
/*  82 */           double up = 0.0D;
/*  83 */           if (getCountOnUpdate() < 10) up = 20.0D; 
/*  84 */           double x = this.targetPosX - this.field_70165_t;
/*  85 */           double y = this.targetPosY + up - this.field_70163_u;
/*  86 */           double z = this.targetPosZ - this.field_70161_v;
/*  87 */           double d = MathHelper.func_76133_a(x * x + y * y + z * z);
/*  88 */           this.field_70159_w = x * this.acceleration / d;
/*  89 */           this.field_70181_x = y * this.acceleration / d;
/*  90 */           this.field_70179_y = z * this.acceleration / d;
/*     */         }
/*     */         else {
/*     */           
/*  94 */           double x = this.targetPosX - this.field_70165_t;
/*  95 */           double y = this.targetPosY - this.field_70163_u;
/*  96 */           y *= 0.3D;
/*  97 */           double z = this.targetPosZ - this.field_70161_v;
/*  98 */           double d = MathHelper.func_76133_a(x * x + y * y + z * z);
/*  99 */           this.field_70159_w = x * this.acceleration / d;
/* 100 */           this.field_70179_y = z * this.acceleration / d;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 106 */     double a = (float)Math.atan2(this.field_70179_y, this.field_70159_w);
/* 107 */     this.field_70177_z = (float)(a * 180.0D / Math.PI) - 90.0F;
/*     */     
/* 109 */     double r = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 110 */     this.field_70125_A = -((float)(Math.atan2(this.field_70181_x, r) * 180.0D / Math.PI));
/*     */     
/* 112 */     onUpdateBomblet();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sprinkleBomblet() {
/* 118 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 120 */       mcheli.weapon.MCH_EntityASMissile e = new mcheli.weapon.MCH_EntityASMissile(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70159_w, this.field_70181_x, this.field_70179_y, this.field_70146_Z.nextInt(360), 0.0F, this.acceleration);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 125 */       e.setParameterFromWeapon(this, this.shootingAircraft, this.shootingEntity);
/* 126 */       e.setName(getName());
/*     */       
/* 128 */       float MOTION = 0.5F;
/* 129 */       float RANDOM = (getInfo()).bombletDiff;
/* 130 */       e.field_70159_w = this.field_70159_w * 0.5D + ((this.field_70146_Z.nextFloat() - 0.5F) * RANDOM);
/* 131 */       e.field_70181_x = this.field_70181_x * 0.5D / 2.0D + ((this.field_70146_Z.nextFloat() - 0.5F) * RANDOM / 2.0F);
/* 132 */       e.field_70179_y = this.field_70179_y * 0.5D + ((this.field_70146_Z.nextFloat() - 0.5F) * RANDOM);
/* 133 */       e.setBomblet();
/*     */       
/* 135 */       this.field_70170_p.func_72838_d((Entity)e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_EntityASMissile(World par1World, double posX, double posY, double posZ, double targetX, double targetY, double targetZ, float yaw, float pitch, double acceleration) {
/* 143 */     super(par1World, posX, posY, posZ, targetX, targetY, targetZ, yaw, pitch, acceleration);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_BulletModel getDefaultBulletModel() {
/* 149 */     return MCH_DefaultBulletModels.ASMissile;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityASMissile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */