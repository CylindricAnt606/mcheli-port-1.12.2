/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import java.util.List;
/*     */ import mcheli.weapon.MCH_BulletModel;
/*     */ import mcheli.weapon.MCH_DefaultBulletModels;
/*     */ import mcheli.weapon.MCH_EntityBaseBullet;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class MCH_EntityBomb
/*     */   extends MCH_EntityBaseBullet
/*     */ {
/*     */   public MCH_EntityBomb(World par1World) {
/*  18 */     super(par1World);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_EntityBomb(World par1World, double posX, double posY, double posZ, double targetX, double targetY, double targetZ, float yaw, float pitch, double acceleration) {
/*  25 */     super(par1World, posX, posY, posZ, targetX, targetY, targetZ, yaw, pitch, acceleration);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  31 */     super.func_70071_h_();
/*     */     
/*  33 */     if (!this.field_70170_p.field_72995_K && getInfo() != null) {
/*     */       
/*  35 */       this.field_70159_w *= 0.999D;
/*  36 */       this.field_70179_y *= 0.999D;
/*     */       
/*  38 */       if (func_70090_H()) {
/*     */         
/*  40 */         this.field_70159_w *= (getInfo()).velocityInWater;
/*  41 */         this.field_70181_x *= (getInfo()).velocityInWater;
/*  42 */         this.field_70179_y *= (getInfo()).velocityInWater;
/*     */       } 
/*     */       
/*  45 */       float dist = (getInfo()).proximityFuseDist;
/*  46 */       if (dist > 0.1F && getCountOnUpdate() % 10 == 0) {
/*     */         
/*  48 */         List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(dist, dist, dist));
/*     */         
/*  50 */         if (list != null)
/*     */         {
/*  52 */           for (int i = 0; i < list.size(); i++) {
/*     */             
/*  54 */             Entity entity = list.get(i);
/*     */             
/*  56 */             if (W_Lib.isEntityLivingBase(entity) && canBeCollidedEntity(entity)) {
/*     */               
/*  58 */               MovingObjectPosition m = new MovingObjectPosition((int)(this.field_70165_t + 0.5D), (int)(this.field_70163_u + 0.5D), (int)(this.field_70161_v + 0.5D), 0, Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*  64 */               onImpact(m, 1.0F);
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*  72 */     onUpdateBomblet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sprinkleBomblet() {
/*  79 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/*  81 */       mcheli.weapon.MCH_EntityBomb e = new mcheli.weapon.MCH_EntityBomb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70159_w, this.field_70181_x, this.field_70179_y, this.field_70146_Z.nextInt(360), 0.0F, this.acceleration);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  86 */       e.setParameterFromWeapon(this, this.shootingAircraft, this.shootingEntity);
/*  87 */       e.setName(getName());
/*     */       
/*  89 */       float MOTION = 1.0F;
/*  90 */       float RANDOM = (getInfo()).bombletDiff;
/*  91 */       e.field_70159_w = this.field_70159_w * 1.0D + ((this.field_70146_Z.nextFloat() - 0.5F) * RANDOM);
/*  92 */       e.field_70181_x = this.field_70181_x * 1.0D / 2.0D + ((this.field_70146_Z.nextFloat() - 0.5F) * RANDOM / 2.0F);
/*  93 */       e.field_70179_y = this.field_70179_y * 1.0D + ((this.field_70146_Z.nextFloat() - 0.5F) * RANDOM);
/*  94 */       e.setBomblet();
/*     */       
/*  96 */       this.field_70170_p.func_72838_d((Entity)e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_BulletModel getDefaultBulletModel() {
/* 103 */     return MCH_DefaultBulletModels.Bomb;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityBomb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */