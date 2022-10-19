/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.weapon.MCH_EntityRocket;
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_WeaponRocket extends MCH_WeaponBase {
/*    */   public MCH_WeaponRocket(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 14 */     super(w, v, yaw, pitch, nm, wi);
/* 15 */     this.acceleration = 4.0F;
/* 16 */     this.explosionPower = 3;
/* 17 */     this.power = 22;
/* 18 */     this.interval = 5;
/* 19 */     if (w.field_72995_K) this.interval += 2; 
/*    */   }
/*    */   
/*    */   public String getName() {
/* 23 */     return super.getName() + ((getCurrentMode() == 0) ? "" : " [HEIAP]");
/*    */   }
/*    */   
/*    */   public boolean shot(MCH_WeaponParam prm) {
/* 27 */     if (!this.worldObj.field_72995_K) {
/*    */       
/* 29 */       playSound(prm.entity);
/*    */       
/* 31 */       Vec3 v = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -prm.rotYaw, -prm.rotPitch, -prm.rotRoll);
/*    */       
/* 33 */       MCH_EntityRocket e = new MCH_EntityRocket(this.worldObj, prm.posX, prm.posY, prm.posZ, v.field_72450_a, v.field_72448_b, v.field_72449_c, prm.rotYaw, prm.rotPitch, this.acceleration);
/*    */ 
/*    */       
/* 36 */       e.setName(this.name);
/*    */       
/* 38 */       e.setParameterFromWeapon(this, prm.entity, prm.user);
/* 39 */       if (prm.option1 == 0 && this.numMode > 1) e.piercing = 0;
/*    */       
/* 41 */       this.worldObj.func_72838_d((Entity)e);
/*    */     }
/*    */     else {
/*    */       
/* 45 */       this.optionParameter1 = getCurrentMode();
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponRocket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */