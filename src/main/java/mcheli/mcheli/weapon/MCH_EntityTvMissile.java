/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.weapon.MCH_BulletModel;
/*    */ import mcheli.weapon.MCH_DefaultBulletModels;
/*    */ import mcheli.weapon.MCH_EntityBaseBullet;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_EntityTvMissile
/*    */   extends MCH_EntityBaseBullet {
/*    */   public MCH_EntityTvMissile(World par1World) {
/* 14 */     super(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSpawnParticle = true;
/*    */   
/*    */   public MCH_EntityTvMissile(World par1World, double posX, double posY, double posZ, double targetX, double targetY, double targetZ, float yaw, float pitch, double acceleration) {
/* 21 */     super(par1World, posX, posY, posZ, targetX, targetY, targetZ, yaw, pitch, acceleration);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 27 */     super.func_70071_h_();
/* 28 */     if (this.isSpawnParticle)
/*    */     {
/* 30 */       if (getInfo() != null && !(getInfo()).disableSmoke)
/*    */       {
/* 32 */         spawnParticle((getInfo()).trajectoryParticleName, 3, 5.0F * (getInfo()).smokeSize * 0.5F);
/*    */       }
/*    */     }
/*    */     
/* 36 */     if (this.shootingEntity != null) {
/*    */       
/* 38 */       double x = this.field_70165_t - this.shootingEntity.field_70165_t;
/* 39 */       double y = this.field_70163_u - this.shootingEntity.field_70163_u;
/* 40 */       double z = this.field_70161_v - this.shootingEntity.field_70161_v;
/* 41 */       if (x * x + y * y + z * z > 1440000.0D)
/*    */       {
/* 43 */         func_70106_y();
/*    */       }
/* 45 */       if (!this.field_70170_p.field_72995_K && !this.field_70128_L)
/*    */       {
/* 47 */         onUpdateMotion();
/*    */       
/*    */       }
/*    */     
/*    */     }
/* 52 */     else if (!this.field_70170_p.field_72995_K) {
/*    */       
/* 54 */       func_70106_y();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdateMotion() {
/* 61 */     Entity e = this.shootingEntity;
/* 62 */     if (e != null && !e.field_70128_L) {
/*    */       
/* 64 */       MCH_EntityAircraft ac = MCH_EntityAircraft.getAircraft_RiddenOrControl(e);
/* 65 */       if (ac != null && ac.getTVMissile() == this) {
/*    */         
/* 67 */         float yaw = e.field_70177_z;
/* 68 */         float pitch = e.field_70125_A;
/* 69 */         double tX = (-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*    */         
/* 71 */         double tZ = (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*    */         
/* 73 */         double tY = -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F);
/* 74 */         setMotion(tX, tY, tZ);
/* 75 */         func_70101_b(yaw, pitch);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MCH_BulletModel getDefaultBulletModel() {
/* 83 */     return MCH_DefaultBulletModels.ATMissile;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityTvMissile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */