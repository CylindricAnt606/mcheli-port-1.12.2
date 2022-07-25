/*    */ package mcheli.mcheli.weapon;
/*    */ import mcheli.weapon.MCH_BulletModel;
/*    */ import mcheli.weapon.MCH_DefaultBulletModels;
/*    */ import mcheli.weapon.MCH_EntityBaseBullet;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_EntityAAMissile extends MCH_EntityBaseBullet {
/*    */   public MCH_EntityAAMissile(World par1World) {
/* 10 */     super(par1World);
/* 11 */     this.targetEntity = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MCH_EntityAAMissile(World par1World, double posX, double posY, double posZ, double targetX, double targetY, double targetZ, float yaw, float pitch, double acceleration) {
/* 18 */     super(par1World, posX, posY, posZ, targetX, targetY, targetZ, yaw, pitch, acceleration);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 24 */     super.func_70071_h_();
/*    */     
/* 26 */     if (getCountOnUpdate() > 4)
/*    */     {
/* 28 */       if (getInfo() != null && !(getInfo()).disableSmoke)
/*    */       {
/* 30 */         spawnParticle((getInfo()).trajectoryParticleName, 3, 7.0F * (getInfo()).smokeSize * 0.5F);
/*    */       }
/*    */     }
/*    */     
/* 34 */     if (!this.field_70170_p.field_72995_K && getInfo() != null)
/*    */     {
/* 36 */       if (this.shootingEntity != null && this.targetEntity != null && !this.targetEntity.field_70128_L) {
/*    */         
/* 38 */         double x = this.field_70165_t - this.targetEntity.field_70165_t;
/* 39 */         double y = this.field_70163_u - this.targetEntity.field_70163_u;
/* 40 */         double z = this.field_70161_v - this.targetEntity.field_70161_v;
/* 41 */         double d = x * x + y * y + z * z;
/*    */         
/* 43 */         if (d > 3422500.0D)
/*    */         {
/* 45 */           func_70106_y();
/*    */ 
/*    */         
/*    */         }
/* 49 */         else if (getCountOnUpdate() > (getInfo()).rigidityTime)
/*    */         {
/*    */           
/* 52 */           if ((getInfo()).proximityFuseDist >= 0.1F && d < (getInfo()).proximityFuseDist)
/*    */           {
/* 54 */             MovingObjectPosition mop = new MovingObjectPosition(this.targetEntity);
/* 55 */             this.field_70165_t = (this.targetEntity.field_70165_t + this.field_70165_t) / 2.0D;
/* 56 */             this.field_70163_u = (this.targetEntity.field_70163_u + this.field_70163_u) / 2.0D;
/* 57 */             this.field_70161_v = (this.targetEntity.field_70161_v + this.field_70161_v) / 2.0D;
/* 58 */             onImpact(mop, 1.0F);
/*    */           }
/*    */           else
/*    */           {
/* 62 */             guidanceToTarget(this.targetEntity.field_70165_t, this.targetEntity.field_70163_u, this.targetEntity.field_70161_v);
/*    */           }
/*    */         
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 69 */         func_70106_y();
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MCH_BulletModel getDefaultBulletModel() {
/* 77 */     return MCH_DefaultBulletModels.AAMissile;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityAAMissile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */