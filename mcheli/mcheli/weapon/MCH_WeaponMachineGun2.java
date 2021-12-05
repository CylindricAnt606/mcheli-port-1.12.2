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
/*    */ public class MCH_WeaponMachineGun2 extends MCH_WeaponBase {
/*    */   public MCH_WeaponMachineGun2(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 13 */     super(w, v, yaw, pitch, nm, wi);
/* 14 */     this.power = 16;
/* 15 */     this.acceleration = 4.0F;
/* 16 */     this.explosionPower = 1;
/* 17 */     this.interval = 2;
/* 18 */     this.numMode = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void modifyParameters() {
/* 24 */     if (this.explosionPower == 0)
/*    */     {
/* 26 */       this.numMode = 0;
/*    */     }
/*    */   }
/*    */   
/*    */   public String getName() {
/* 31 */     return super.getName() + ((getCurrentMode() == 0) ? "" : " [HE]");
/*    */   }
/*    */   
/*    */   public boolean shot(MCH_WeaponParam prm) {
/* 35 */     if (!this.worldObj.field_72995_K) {
/*    */       
/* 37 */       Vec3 v = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -prm.rotYaw, -prm.rotPitch, -prm.rotRoll);
/*    */       
/* 39 */       MCH_EntityBullet e = new MCH_EntityBullet(this.worldObj, prm.posX, prm.posY, prm.posZ, v.field_72450_a, v.field_72448_b, v.field_72449_c, prm.rotYaw, prm.rotPitch, this.acceleration);
/*    */ 
/*    */       
/* 42 */       e.setName(this.name);
/*    */       
/* 44 */       e.setParameterFromWeapon(this, prm.entity, prm.user);
/*    */       
/* 46 */       if ((getInfo()).modeNum < 2) {
/*    */         
/* 48 */         e.explosionPower = this.explosionPower;
/*    */       }
/*    */       else {
/*    */         
/* 52 */         e.explosionPower = (prm.option1 == 0) ? -this.explosionPower : this.explosionPower;
/*    */       } 
/*    */       
/* 55 */       e.field_70165_t += e.field_70159_w * 0.5D;
/* 56 */       e.field_70163_u += e.field_70181_x * 0.5D;
/* 57 */       e.field_70161_v += e.field_70179_y * 0.5D;
/*    */       
/* 59 */       this.worldObj.func_72838_d((Entity)e);
/*    */       
/* 61 */       playSound(prm.entity);
/*    */     }
/*    */     else {
/*    */       
/* 65 */       this.optionParameter1 = getCurrentMode();
/*    */     } 
/*    */     
/* 68 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponMachineGun2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */