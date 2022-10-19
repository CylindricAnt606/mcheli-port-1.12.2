/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.weapon.MCH_EntityMarkerRocket;
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_WeaponMarkerRocket extends MCH_WeaponBase {
/*    */   public MCH_WeaponMarkerRocket(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 14 */     super(w, v, yaw, pitch, nm, wi);
/* 15 */     this.acceleration = 3.0F;
/* 16 */     this.explosionPower = 0;
/* 17 */     this.power = 0;
/* 18 */     this.interval = 60;
/* 19 */     if (w.field_72995_K) this.interval += 10;
/*    */   
/*    */   }
/*    */   
/*    */   public boolean shot(MCH_WeaponParam prm) {
/* 24 */     if (!this.worldObj.field_72995_K) {
/*    */       
/* 26 */       playSound(prm.entity);
/*    */       
/* 28 */       Vec3 v = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -prm.rotYaw, -prm.rotPitch, -prm.rotRoll);
/*    */       
/* 30 */       MCH_EntityMarkerRocket e = new MCH_EntityMarkerRocket(this.worldObj, prm.posX, prm.posY, prm.posZ, v.field_72450_a, v.field_72448_b, v.field_72449_c, prm.rotYaw, prm.rotPitch, this.acceleration);
/*    */ 
/*    */       
/* 33 */       e.setName(this.name);
/*    */       
/* 35 */       e.setParameterFromWeapon(this, prm.entity, prm.user);
/* 36 */       e.setMarkerStatus(1);
/*    */       
/* 38 */       this.worldObj.func_72838_d((Entity)e);
/*    */     }
/*    */     else {
/*    */       
/* 42 */       this.optionParameter1 = getCurrentMode();
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponMarkerRocket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */