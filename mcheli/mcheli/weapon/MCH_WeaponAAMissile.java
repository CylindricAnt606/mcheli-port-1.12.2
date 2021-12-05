/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import mcheli.weapon.MCH_EntityAAMissile;
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponEntitySeeker;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import mcheli.wrapper.W_Entity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_WeaponAAMissile extends MCH_WeaponEntitySeeker {
/*    */   public MCH_WeaponAAMissile(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 16 */     super(w, v, yaw, pitch, nm, wi);
/* 17 */     this.power = 12;
/* 18 */     this.acceleration = 2.5F;
/* 19 */     this.explosionPower = 4;
/* 20 */     this.interval = 5;
/* 21 */     if (w.field_72995_K) this.interval += 5;
/*    */     
/* 23 */     this.guidanceSystem.canLockInAir = true;
/* 24 */     this.guidanceSystem.ridableOnly = wi.ridableOnly;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCooldownCountReloadTime() {
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update(int countWait) {
/* 35 */     super.update(countWait);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shot(MCH_WeaponParam prm) {
/* 40 */     boolean result = false;
/* 41 */     if (!this.worldObj.field_72995_K) {
/*    */       
/* 43 */       Entity tgtEnt = prm.user.field_70170_p.func_73045_a(prm.option1);
/*    */       
/* 45 */       if (tgtEnt != null && !tgtEnt.field_70128_L)
/*    */       {
/* 47 */         playSound(prm.entity);
/*    */         
/* 49 */         float yaw = prm.entity.field_70177_z + this.fixRotationYaw;
/* 50 */         float pitch = prm.entity.field_70125_A + this.fixRotationPitch;
/*    */         
/* 52 */         double tX = (-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*    */         
/* 54 */         double tZ = (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*    */         
/* 56 */         double tY = -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F);
/*    */         
/* 58 */         MCH_EntityAAMissile e = new MCH_EntityAAMissile(this.worldObj, prm.posX, prm.posY, prm.posZ, tX, tY, tZ, yaw, pitch, this.acceleration);
/*    */         
/* 60 */         e.setName(this.name);
/*    */         
/* 62 */         e.setParameterFromWeapon((MCH_WeaponBase)this, prm.entity, prm.user);
/*    */         
/* 64 */         e.setTargetEntity(tgtEnt);
/*    */         
/* 66 */         this.worldObj.func_72838_d((Entity)e);
/* 67 */         result = true;
/*    */       
/*    */       }
/*    */     
/*    */     }
/* 72 */     else if (this.guidanceSystem.lock(prm.user)) {
/*    */       
/* 74 */       if (this.guidanceSystem.lastLockEntity != null) {
/*    */         
/* 76 */         result = true;
/* 77 */         this.optionParameter1 = W_Entity.getEntityId(this.guidanceSystem.lastLockEntity);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 82 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponAAMissile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */