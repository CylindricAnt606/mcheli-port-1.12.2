/*    */ package mcheli.mcheli.weapon;
/*    */ import mcheli.weapon.MCH_EntityASMissile;
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import mcheli.wrapper.W_WorldFunc;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_WeaponASMissile extends MCH_WeaponBase {
/*    */   public MCH_WeaponASMissile(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 14 */     super(w, v, yaw, pitch, nm, wi);
/* 15 */     this.acceleration = 3.0F;
/* 16 */     this.explosionPower = 9;
/* 17 */     this.power = 40;
/* 18 */     this.interval = -350;
/* 19 */     if (w.field_72995_K) this.interval -= 10;
/*    */   
/*    */   }
/*    */   
/*    */   public boolean isCooldownCountReloadTime() {
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update(int countWait) {
/* 30 */     super.update(countWait);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shot(MCH_WeaponParam prm) {
/* 35 */     float yaw = prm.user.field_70177_z;
/* 36 */     float pitch = prm.user.field_70125_A;
/*    */     
/* 38 */     double tX = (-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*    */     
/* 40 */     double tZ = (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*    */     
/* 42 */     double tY = -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F);
/*    */     
/* 44 */     double dist = MathHelper.func_76133_a(tX * tX + tY * tY + tZ * tZ);
/*    */ 
/*    */     
/* 47 */     if (this.worldObj.field_72995_K) {
/*    */       
/* 49 */       tX = tX * 200.0D / dist;
/* 50 */       tY = tY * 200.0D / dist;
/* 51 */       tZ = tZ * 200.0D / dist;
/*    */     }
/*    */     else {
/*    */       
/* 55 */       tX = tX * 250.0D / dist;
/* 56 */       tY = tY * 250.0D / dist;
/* 57 */       tZ = tZ * 250.0D / dist;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 62 */     Vec3 src = W_WorldFunc.getWorldVec3(this.worldObj, prm.entity.field_70165_t, prm.entity.field_70163_u + 1.62D, prm.entity.field_70161_v);
/* 63 */     Vec3 dst = W_WorldFunc.getWorldVec3(this.worldObj, prm.entity.field_70165_t + tX, prm.entity.field_70163_u + 1.62D + tY, prm.entity.field_70161_v + tZ);
/* 64 */     MovingObjectPosition m = W_WorldFunc.clip(this.worldObj, src, dst);
/*    */     
/* 66 */     if (m != null && W_MovingObjectPosition.isHitTypeTile(m) && !MCH_Lib.isBlockInWater(this.worldObj, m.field_72311_b, m.field_72312_c, m.field_72309_d)) {
/*    */ 
/*    */ 
/*    */       
/* 70 */       if (!this.worldObj.field_72995_K) {
/*    */         
/* 72 */         MCH_EntityASMissile e = new MCH_EntityASMissile(this.worldObj, prm.posX, prm.posY, prm.posZ, tX, tY, tZ, yaw, pitch, this.acceleration);
/*    */         
/* 74 */         e.setName(this.name);
/*    */         
/* 76 */         e.setParameterFromWeapon(this, prm.entity, prm.user);
/*    */         
/* 78 */         e.targetPosX = m.field_72307_f.field_72450_a;
/* 79 */         e.targetPosY = m.field_72307_f.field_72448_b;
/* 80 */         e.targetPosZ = m.field_72307_f.field_72449_c;
/*    */         
/* 82 */         this.worldObj.func_72838_d((Entity)e);
/*    */         
/* 84 */         playSound(prm.entity);
/*    */       } 
/*    */       
/* 87 */       return true;
/*    */     } 
/* 89 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponASMissile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */