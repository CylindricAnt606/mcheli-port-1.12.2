/*    */ package mcheli.mcheli.weapon;
/*    */ import mcheli.weapon.MCH_DefaultBulletModels;
/*    */ import mcheli.weapon.MCH_EntityBaseBullet;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_EntityRocket extends MCH_EntityBaseBullet {
/*    */   public MCH_EntityRocket(World par1World) {
/*  9 */     super(par1World);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MCH_EntityRocket(World par1World, double posX, double posY, double posZ, double targetX, double targetY, double targetZ, float yaw, float pitch, double acceleration) {
/* 16 */     super(par1World, posX, posY, posZ, targetX, targetY, targetZ, yaw, pitch, acceleration);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 22 */     super.func_70071_h_();
/*    */     
/* 24 */     onUpdateBomblet();
/*    */ 
/*    */     
/* 27 */     if (this.isBomblet <= 0)
/*    */     {
/* 29 */       if (getInfo() != null && !(getInfo()).disableSmoke)
/*    */       {
/* 31 */         spawnParticle((getInfo()).trajectoryParticleName, 3, 5.0F * (getInfo()).smokeSize * 0.5F);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void sprinkleBomblet() {
/* 38 */     if (!this.field_70170_p.field_72995_K) {
/*    */       
/* 40 */       mcheli.weapon.MCH_EntityRocket e = new mcheli.weapon.MCH_EntityRocket(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70159_w, this.field_70181_x, this.field_70179_y, this.field_70177_z, this.field_70125_A, this.acceleration);
/*    */ 
/*    */ 
/*    */       
/* 44 */       e.setName(getName());
/*    */       
/* 46 */       e.setParameterFromWeapon(this, this.shootingAircraft, this.shootingEntity);
/*    */       
/* 48 */       float MOTION = (getInfo()).bombletDiff;
/* 49 */       float RANDOM = 1.2F;
/* 50 */       e.field_70159_w += (this.field_70146_Z.nextFloat() - 0.5D) * MOTION;
/* 51 */       e.field_70181_x += (this.field_70146_Z.nextFloat() - 0.5D) * MOTION;
/* 52 */       e.field_70179_y += (this.field_70146_Z.nextFloat() - 0.5D) * MOTION;
/* 53 */       e.setBomblet();
/*    */       
/* 55 */       this.field_70170_p.func_72838_d((Entity)e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MCH_BulletModel getDefaultBulletModel() {
/* 62 */     return MCH_DefaultBulletModels.Rocket;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityRocket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */