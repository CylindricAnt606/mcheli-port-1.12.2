/*    */ package mcheli.mcheli.weapon;
/*    */ import mcheli.weapon.MCH_EntityDispensedItem;
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_WeaponDispenser extends MCH_WeaponBase {
/*    */   public MCH_WeaponDispenser(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 11 */     super(w, v, yaw, pitch, nm, wi);
/* 12 */     this.acceleration = 0.5F;
/* 13 */     this.explosionPower = 0;
/* 14 */     this.power = 0;
/* 15 */     this.interval = -90;
/* 16 */     if (w.field_72995_K) this.interval -= 10;
/*    */   
/*    */   }
/*    */   
/*    */   public boolean shot(MCH_WeaponParam prm) {
/* 21 */     if (!this.worldObj.field_72995_K) {
/*    */       
/* 23 */       playSound(prm.entity);
/*    */       
/* 25 */       Vec3 v = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -prm.rotYaw, -prm.rotPitch, -prm.rotRoll);
/*    */       
/* 27 */       MCH_EntityDispensedItem e = new MCH_EntityDispensedItem(this.worldObj, prm.posX, prm.posY, prm.posZ, v.field_72450_a, v.field_72448_b, v.field_72449_c, prm.rotYaw, prm.rotPitch, this.acceleration);
/*    */ 
/*    */       
/* 30 */       e.setName(this.name);
/*    */       
/* 32 */       e.setParameterFromWeapon(this, prm.entity, prm.user);
/*    */       
/* 34 */       e.field_70159_w = prm.entity.field_70159_w + e.field_70159_w * 0.5D;
/* 35 */       e.field_70181_x = prm.entity.field_70181_x + e.field_70181_x * 0.5D;
/* 36 */       e.field_70179_y = prm.entity.field_70179_y + e.field_70179_y * 0.5D;
/* 37 */       e.field_70165_t += e.field_70159_w * 0.5D;
/* 38 */       e.field_70163_u += e.field_70181_x * 0.5D;
/* 39 */       e.field_70161_v += e.field_70179_y * 0.5D;
/*    */       
/* 41 */       this.worldObj.func_72838_d((Entity)e);
/*    */     } 
/*    */     
/* 44 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponDispenser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */