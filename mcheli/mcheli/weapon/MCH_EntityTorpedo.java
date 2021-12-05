/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import mcheli.weapon.MCH_BulletModel;
/*     */ import mcheli.weapon.MCH_DefaultBulletModels;
/*     */ import mcheli.weapon.MCH_EntityBaseBullet;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_EntityTorpedo extends MCH_EntityBaseBullet {
/*     */   public double targetPosX;
/*  11 */   public double accelerationInWater = 2.0D;
/*     */   public double targetPosY;
/*     */   
/*     */   public MCH_EntityTorpedo(World par1World) {
/*  15 */     super(par1World);
/*  16 */     this.targetPosX = 0.0D;
/*  17 */     this.targetPosY = 0.0D;
/*  18 */     this.targetPosZ = 0.0D;
/*     */   }
/*     */   
/*     */   public double targetPosZ;
/*     */   
/*     */   public void func_70071_h_() {
/*  24 */     super.func_70071_h_();
/*     */     
/*  26 */     if (getInfo() != null && (getInfo()).isGuidedTorpedo) {
/*     */       
/*  28 */       onUpdateGuided();
/*     */     }
/*     */     else {
/*     */       
/*  32 */       onUpdateNoGuided();
/*     */     } 
/*     */     
/*  35 */     if (func_70090_H())
/*     */     {
/*  37 */       if (getInfo() != null && !(getInfo()).disableSmoke)
/*     */       {
/*  39 */         spawnParticle((getInfo()).trajectoryParticleName, 3, 5.0F * (getInfo()).smokeSize * 0.5F);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void onUpdateNoGuided() {
/*  46 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/*  48 */       if (func_70090_H()) {
/*     */         
/*  50 */         this.field_70181_x *= 0.800000011920929D;
/*  51 */         if (this.acceleration < this.accelerationInWater) {
/*     */           
/*  53 */           this.acceleration += 0.1D;
/*     */         }
/*  55 */         else if (this.acceleration > this.accelerationInWater + 0.20000000298023224D) {
/*     */           
/*  57 */           this.acceleration -= 0.1D;
/*     */         } 
/*  59 */         double x = this.field_70159_w;
/*  60 */         double y = this.field_70181_x;
/*  61 */         double z = this.field_70179_y;
/*  62 */         double d = MathHelper.func_76133_a(x * x + y * y + z * z);
/*  63 */         this.field_70159_w = x * this.acceleration / d;
/*  64 */         this.field_70181_x = y * this.acceleration / d;
/*  65 */         this.field_70179_y = z * this.acceleration / d;
/*     */       } 
/*     */     }
/*  68 */     if (func_70090_H()) {
/*     */       
/*  70 */       double a = (float)Math.atan2(this.field_70179_y, this.field_70159_w);
/*  71 */       this.field_70177_z = (float)(a * 180.0D / Math.PI) - 90.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onUpdateGuided() {
/*  77 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/*  79 */       if (func_70090_H()) {
/*     */         
/*  81 */         if (this.acceleration < this.accelerationInWater) {
/*     */           
/*  83 */           this.acceleration += 0.1D;
/*     */         }
/*  85 */         else if (this.acceleration > this.accelerationInWater + 0.20000000298023224D) {
/*     */           
/*  87 */           this.acceleration -= 0.1D;
/*     */         } 
/*  89 */         double x = this.targetPosX - this.field_70165_t;
/*  90 */         double y = this.targetPosY - this.field_70163_u;
/*  91 */         double z = this.targetPosZ - this.field_70161_v;
/*  92 */         double d = MathHelper.func_76133_a(x * x + y * y + z * z);
/*  93 */         this.field_70159_w = x * this.acceleration / d;
/*  94 */         this.field_70181_x = y * this.acceleration / d;
/*  95 */         this.field_70179_y = z * this.acceleration / d;
/*     */       } 
/*     */     }
/*     */     
/*  99 */     if (func_70090_H()) {
/*     */       
/* 101 */       double a = (float)Math.atan2(this.field_70179_y, this.field_70159_w);
/* 102 */       this.field_70177_z = (float)(a * 180.0D / Math.PI) - 90.0F;
/*     */       
/* 104 */       double r = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 105 */       this.field_70125_A = -((float)(Math.atan2(this.field_70181_x, r) * 180.0D / Math.PI));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_EntityTorpedo(World par1World, double posX, double posY, double posZ, double targetX, double targetY, double targetZ, float yaw, float pitch, double acceleration) {
/* 113 */     super(par1World, posX, posY, posZ, targetX, targetY, targetZ, yaw, pitch, acceleration);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_BulletModel getDefaultBulletModel() {
/* 119 */     return MCH_DefaultBulletModels.Torpedo;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityTorpedo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */