/*    */ package mcheli.mcheli.weapon;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.weapon.MCH_EntityBomb;
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_WeaponBomb extends MCH_WeaponBase {
/*    */   public MCH_WeaponBomb(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 13 */     super(w, v, yaw, pitch, nm, wi);
/* 14 */     this.acceleration = 0.5F;
/* 15 */     this.explosionPower = 9;
/* 16 */     this.power = 35;
/* 17 */     this.interval = -90;
/* 18 */     if (w.field_72995_K) this.interval -= 10;
/*    */   
/*    */   }
/*    */   
/*    */   public boolean shot(MCH_WeaponParam prm) {
/* 23 */     if (getInfo() != null && (getInfo()).destruct) {
/*    */       
/* 25 */       if (prm.entity instanceof mcheli.helicopter.MCH_EntityHeli) {
/*    */         
/* 27 */         MCH_EntityAircraft ac = (MCH_EntityAircraft)prm.entity;
/* 28 */         if (ac.isUAV() && ac.getSeatNum() == 0)
/*    */         {
/* 30 */           if (!this.worldObj.field_72995_K) {
/*    */             
/* 32 */             MCH_Explosion.newExplosion(this.worldObj, null, prm.user, ac.field_70165_t, ac.field_70163_u, ac.field_70161_v, (getInfo()).explosion, (getInfo()).explosionBlock, true, true, (getInfo()).flaming, true, 0);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 41 */             playSound(prm.entity);
/*    */           } 
/* 43 */           ac.destruct();
/*    */         }
/*    */       
/*    */       } 
/* 47 */     } else if (!this.worldObj.field_72995_K) {
/*    */       
/* 49 */       playSound(prm.entity);
/*    */       
/* 51 */       MCH_EntityBomb e = new MCH_EntityBomb(this.worldObj, prm.posX, prm.posY, prm.posZ, prm.entity.field_70159_w, prm.entity.field_70181_x, prm.entity.field_70179_y, prm.entity.field_70177_z, 0.0F, this.acceleration);
/*    */ 
/*    */ 
/*    */       
/* 55 */       e.setName(this.name);
/*    */       
/* 57 */       e.setParameterFromWeapon(this, prm.entity, prm.user);
/*    */       
/* 59 */       e.field_70159_w = prm.entity.field_70159_w;
/* 60 */       e.field_70181_x = prm.entity.field_70181_x;
/* 61 */       e.field_70179_y = prm.entity.field_70179_y;
/*    */       
/* 63 */       this.worldObj.func_72838_d((Entity)e);
/*    */     } 
/*    */     
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponBomb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */