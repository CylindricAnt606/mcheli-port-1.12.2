/*    */ package mcheli.mcheli.weapon;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.weapon.MCH_EntityBullet;
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_WeaponMachineGun1 extends MCH_WeaponBase {
/*    */   public MCH_WeaponMachineGun1(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 13 */     super(w, v, yaw, pitch, nm, wi);
/* 14 */     this.power = 8;
/* 15 */     this.acceleration = 4.0F;
/* 16 */     this.explosionPower = 0;
/* 17 */     this.interval = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shot(MCH_WeaponParam prm) {
/* 22 */     if (!this.worldObj.field_72995_K) {
/*    */       
/* 24 */       Vec3 v = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -prm.rotYaw, -prm.rotPitch, -prm.rotRoll);
/*    */       
/* 26 */       MCH_EntityBullet e = new MCH_EntityBullet(this.worldObj, prm.posX, prm.posY, prm.posZ, v.field_72450_a, v.field_72448_b, v.field_72449_c, prm.rotYaw, prm.rotPitch, this.acceleration);
/*    */ 
/*    */       
/* 29 */       e.setName(this.name);
/*    */       
/* 31 */       e.setParameterFromWeapon(this, prm.entity, prm.user);
/*    */       
/* 33 */       e.field_70165_t += e.field_70159_w * 0.5D;
/* 34 */       e.field_70163_u += e.field_70181_x * 0.5D;
/* 35 */       e.field_70161_v += e.field_70179_y * 0.5D;
/*    */       
/* 37 */       this.worldObj.func_72838_d((Entity)e);
/*    */       
/* 39 */       playSound(prm.entity);
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponMachineGun1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */