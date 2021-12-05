/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.weapon.MCH_BulletModel;
/*     */ import mcheli.weapon.MCH_DefaultBulletModels;
/*     */ import mcheli.weapon.MCH_EntityBaseBullet;
/*     */ import mcheli.wrapper.W_MovingObjectPosition;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_EntityBullet
/*     */   extends MCH_EntityBaseBullet
/*     */ {
/*     */   public MCH_EntityBullet(World par1World) {
/*  23 */     super(par1World);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_EntityBullet(World par1World, double pX, double pY, double pZ, double targetX, double targetY, double targetZ, float yaw, float pitch, double acceleration) {
/*  31 */     super(par1World, pX, pY, pZ, targetX, targetY, targetZ, yaw, pitch, acceleration);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  37 */     super.func_70071_h_();
/*     */     
/*  39 */     if (!this.field_70128_L && !this.field_70170_p.field_72995_K && getCountOnUpdate() > 1 && getInfo() != null && this.explosionPower > 0) {
/*     */       
/*  41 */       float pDist = (getInfo()).proximityFuseDist;
/*  42 */       if (pDist > 0.1D) {
/*     */         
/*  44 */         pDist++;
/*     */         
/*  46 */         float rng = pDist + MathHelper.func_76135_e((getInfo()).acceleration);
/*  47 */         List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(rng, rng, rng));
/*     */ 
/*     */         
/*  50 */         for (int i = 0; i < list.size(); i++) {
/*     */           
/*  52 */           Entity entity1 = list.get(i);
/*     */ 
/*     */           
/*  55 */           if (canBeCollidedEntity(entity1) && entity1.func_70068_e((Entity)this) < (pDist * pDist)) {
/*     */ 
/*     */             
/*  58 */             MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityBullet.onUpdate:proximityFuse:" + entity1, new Object[0]);
/*     */             
/*  60 */             this.field_70165_t = (entity1.field_70165_t + this.field_70165_t) / 2.0D;
/*  61 */             this.field_70163_u = (entity1.field_70163_u + this.field_70163_u) / 2.0D;
/*  62 */             this.field_70161_v = (entity1.field_70161_v + this.field_70161_v) / 2.0D;
/*     */             
/*  64 */             MovingObjectPosition mop = W_MovingObjectPosition.newMOP((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0, W_WorldFunc.getWorldVec3EntityPos((Entity)this), false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  70 */             onImpact(mop, 1.0F);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onUpdateCollided() {
/*  82 */     double mx = this.field_70159_w * this.accelerationFactor;
/*  83 */     double my = this.field_70181_x * this.accelerationFactor;
/*  84 */     double mz = this.field_70179_y * this.accelerationFactor;
/*     */     
/*  86 */     float damageFactor = 1.0F;
/*     */ 
/*     */     
/*  89 */     MovingObjectPosition m = null;
/*  90 */     for (int i = 0; i < 5; i++) {
/*     */       
/*  92 */       Vec3 vec32 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*  93 */       Vec3 vec33 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t + mx, this.field_70163_u + my, this.field_70161_v + mz);
/*  94 */       m = W_WorldFunc.clip(this.field_70170_p, vec32, vec33);
/*     */       
/*  96 */       boolean continueClip = false;
/*     */       
/*  98 */       if (this.shootingEntity != null && W_MovingObjectPosition.isHitTypeTile(m)) {
/*     */         
/* 100 */         Block block = W_WorldFunc.getBlock(this.field_70170_p, m.field_72311_b, m.field_72312_c, m.field_72309_d);
/* 101 */         if (MCH_Config.bulletBreakableBlocks.contains(block)) {
/*     */           
/* 103 */           W_WorldFunc.destroyBlock(this.field_70170_p, m.field_72311_b, m.field_72312_c, m.field_72309_d, true);
/*     */           
/* 105 */           continueClip = true;
/*     */         } 
/*     */       } 
/*     */       
/* 109 */       if (!continueClip) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 115 */     Vec3 vec3 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 116 */     Vec3 vec31 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t + mx, this.field_70163_u + my, this.field_70161_v + mz);
/*     */     
/* 118 */     if ((getInfo()).delayFuse > 0) {
/*     */       
/* 120 */       if (m != null) {
/*     */         
/* 122 */         boundBullet(m.field_72310_e);
/* 123 */         if (this.delayFuse == 0)
/*     */         {
/* 125 */           this.delayFuse = (getInfo()).delayFuse;
/*     */         }
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 131 */     if (m != null)
/*     */     {
/* 133 */       vec31 = W_WorldFunc.getWorldVec3(this.field_70170_p, m.field_72307_f.field_72450_a, m.field_72307_f.field_72448_b, m.field_72307_f.field_72449_c);
/*     */     }
/*     */     
/* 136 */     Entity entity = null;
/* 137 */     List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(mx, my, mz).func_72314_b(21.0D, 21.0D, 21.0D));
/* 138 */     double d0 = 0.0D;
/*     */     
/* 140 */     for (int j = 0; j < list.size(); j++) {
/*     */       
/* 142 */       Entity entity1 = list.get(j);
/*     */ 
/*     */       
/* 145 */       if (canBeCollidedEntity(entity1)) {
/*     */         
/* 147 */         float f = 0.3F;
/* 148 */         AxisAlignedBB axisalignedbb = entity1.field_70121_D.func_72314_b(f, f, f);
/* 149 */         MovingObjectPosition m1 = axisalignedbb.func_72327_a(vec3, vec31);
/*     */         
/* 151 */         if (m1 != null) {
/*     */           
/* 153 */           double d1 = vec3.func_72438_d(m1.field_72307_f);
/*     */           
/* 155 */           if (d1 < d0 || d0 == 0.0D) {
/*     */             
/* 157 */             entity = entity1;
/* 158 */             d0 = d1;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     if (entity != null)
/*     */     {
/* 189 */       m = new MovingObjectPosition(entity);
/*     */     }
/*     */     
/* 192 */     if (m != null)
/*     */     {
/* 194 */       onImpact(m, damageFactor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_BulletModel getDefaultBulletModel() {
/* 201 */     return MCH_DefaultBulletModels.Bullet;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityBullet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */